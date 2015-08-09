package tw.com.skywing.whiteboard;

import android.graphics.Color;
import android.graphics.Paint;

public class PenSet {

	private Paint paint;

	public PenSet() {

		paint = new Paint();

		setNewPaintColor(1); // �O�����P�ɫ

		setNewPaintSize(1); // �O�����P��С

		paint.setStrokeCap(Paint.Cap.ROUND); // �P���Π�

		paint.setStrokeJoin(Paint.Join.ROUND); // �D���Π�

		paint.setStyle(Paint.Style.STROKE); // ���Į��P

		paint.setDither(true); // �p�ٮ�������

		paint.setAntiAlias(true); // ���P����X

	}

	public Paint getNewPaint() {

		return paint;

	}

	public void setNewPaintSize(int penSize) {

		switch (penSize) {

		case 1:

			paint.setStrokeWidth(2);

			break;

		case 2:

			paint.setStrokeWidth(5);

			break;

		case 3:

			paint.setStrokeWidth(8);

			break;

		case 5:

			paint.setStrokeWidth(10);

			break;

		default:

			paint.setStrokeWidth(2);

			break;

		}

	}

	public void setNewPaintColor(int penColor) {

		switch (penColor) {

		case 1:

			paint.setColor(Color.BLACK);

			break;

		case 2:

			paint.setColor(Color.BLUE);

			break;

		case 3:

			paint.setColor(Color.RED);

			break;

		case 4:

			paint.setColor(Color.GREEN);

			break;

		case 5:

			paint.setColor(Color.WHITE);

			break;

		default:

			paint.setColor(Color.BLACK);

			break;

		}

	}

}
