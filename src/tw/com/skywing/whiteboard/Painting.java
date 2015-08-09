package tw.com.skywing.whiteboard;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Painting extends View {

	private Paint paint, eraserPaint, ePaint; // ���P

	private Canvas canvas; // ����

	private HashMap<Integer, Path> pathMap;

	private HashMap<Integer, Point> prePointMap;

	private Bitmap bitmap; // ����ʹ���cꇈD

	private PenSet penSet;

	private Eraser eraser;

	private int touchPoint;

	private boolean isDraw, isEraser;

	private static final int MAX_TOUCH_POINT = 5;

	private static final int TOUCH_TOLERANCE = 2;

	public Painting(Context context, AttributeSet attrs) {

		super(context, attrs);

		penSet = new PenSet();

		eraser = new Eraser();

		paint = penSet.getNewPaint(); // ���P�O��

		eraserPaint = eraser.getEraserPaint(); // ��Ƥ���O��

		ePaint = eraser.getEPaint(); // ��Ƥ������O��

		pathMap = new HashMap<Integer, Path>();

		prePointMap = new HashMap<Integer, Point>();

		isDraw = true;

		isEraser = false;

	}

	public void setPaintMode(String mode) {

		isDraw = false;

		isEraser = false;

		if (mode == "isDraw")

			isDraw = true;

		if (mode == "isEraser")

			isEraser = true;

	}

	public void changePenSize(int penSize) {

		penSet.setNewPaintSize(penSize);

		paint = penSet.getNewPaint();

	}

	public void changePenColor(int penColor) {

		penSet.setNewPaintColor(penColor);

		paint = penSet.getNewPaint();

	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		try {

			// ��bitmap�L������
			canvas.drawBitmap(bitmap, 0, 0, null);

		} catch (Exception e) {

		}

		// ��vpathMap������keyֵ
		for (Integer key : pathMap.keySet())

			canvas.drawPath(pathMap.get(key), paint); // �onDraw�������L�upath

		if (isEraser && ePath != null)

			canvas.drawPath(ePath, ePaint);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		touchPoint = event.getPointerCount();

		int action = event.getActionMasked();

		int index = event.getActionIndex();

		int id = event.getPointerId(index);

		float x = event.getX(index);

		float y = event.getY(index);

		// �|�ذ����¼�
		if (action == MotionEvent.ACTION_DOWN
				|| action == MotionEvent.ACTION_POINTER_DOWN)

			pathPointMove(x, y, id);

		// �|���Ƅ��¼�
		else if (action == MotionEvent.ACTION_MOVE) {

			if (isDraw)

				pathDrawQuadLine(event);

			else if (isEraser)

				eraserMove(event);

		}

		// �|�ط��_�¼�
		else if (action == MotionEvent.ACTION_UP
				|| action == MotionEvent.ACTION_POINTER_UP)

			pathDrawLine(id, x, y);

		postInvalidate(); // ��������

		return true;

	}

	// �O��bitmap��С
	public void setCanvasSize(int disWidth, int disHeigth) {

		bitmap = Bitmap.createBitmap(disWidth, disHeigth, Config.ARGB_8888);

		canvas = new Canvas(bitmap); // �O������ʹ��bitmap

		bitmap.eraseColor(Color.WHITE);

	}

	// ·����ʼ�c�O��
	private void pathPointMove(float x, float y, int id) {

		Path path;

		Point point;

		if (pathMap.containsKey(id)) {

			path = pathMap.get(id);

			point = prePointMap.get(id);

		}

		else {

			path = new Path();

			pathMap.put(id, path);

			point = new Point();

			prePointMap.put(id, point);

		}

		path.moveTo(x, y);

		point.x = (int) x;

		point.y = (int) y;

	}

	// ·��ؐƝ�����L�D
	private void pathDrawQuadLine(MotionEvent event) {

		for (int i = 0; i < touchPoint; i++)

		{
			int id = event.getPointerId(i);

			if (pathMap.containsKey(id))

			{

				int index = event.findPointerIndex(id);

				float nowX = event.getX(index);

				float nowY = event.getY(index);

				Path path = pathMap.get(id);

				Point point = prePointMap.get(id);

				float dx = Math.abs(nowX - point.x);

				float dy = Math.abs(nowY - point.y);

				if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE)

				{

					path.quadTo(point.x, point.y, (nowX + point.x) / 2,
							(nowY + point.y) / 2);

					point.x = (int) nowX;

					point.y = (int) nowY;

				}

			}

		}

	}

	// ·��ֱ���L�D
	private void pathDrawLine(int id, float x, float y) {

		Path path = pathMap.get(id);

		path.lineTo(x, y);

		if (isDraw) {

			canvas.drawPath(path, paint);

		}

		path.reset();

		if (ePath != null)

			ePath.reset();

	}

	int dis;

	Point centerPoint;

	Path ePath;

	private void eraserMove(MotionEvent event) {

		Point[] allPoint = new Point[MAX_TOUCH_POINT];

		for (int i = 0; i < touchPoint; i++) {

			Point point = new Point((int) event.getX(i), (int) event.getY(i));

			allPoint[i] = point;

		}

		dis = 0;

		centerPoint = new Point();

		ePath = new Path();

		if (touchPoint > 1) {

			for (int i = 0; i < touchPoint; i++) {

				for (int j = i + 1; j < touchPoint; j++) {

					int temp = (int) Math.sqrt(Math.pow(allPoint[i].x
							- allPoint[j].x, 2)
							+ Math.pow(allPoint[i].y - allPoint[j].y, 2));

					if (temp > dis) {

						dis = temp;

						centerPoint.x = (allPoint[i].x + allPoint[j].x) / 2;

						centerPoint.y = (allPoint[i].y + allPoint[j].y) / 2;

					}

				}

			}

		} else {

			dis = 50;

			centerPoint.x = (int) event.getX();

			centerPoint.y = (int) event.getY();

		}

		ePath.addCircle(centerPoint.x, centerPoint.y, dis / 2,
				Path.Direction.CW);

		canvas.drawCircle(centerPoint.x, centerPoint.y, dis / 2, eraserPaint);

	}

	// �������
	public void canvasClear() {

		pathMap.clear();

		prePointMap.clear();

		bitmap.eraseColor(Color.WHITE);

		invalidate();

	}

}
