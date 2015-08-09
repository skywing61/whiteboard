package tw.com.skywing.whiteboard;

import android.graphics.Color;
import android.graphics.Paint;

public class PenSet {

	private Paint paint;

	public PenSet() {

		paint = new Paint();

		setNewPaintColor(1); // 設定畫筆顏色

		setNewPaintSize(1); // 設定畫筆大小

		paint.setStrokeCap(Paint.Cap.ROUND); // 筆尖形狀

		paint.setStrokeJoin(Paint.Join.ROUND); // 轉角形狀

		paint.setStyle(Paint.Style.STROKE); // 空心畫筆

		paint.setDither(true); // 減少畫布抖動

		paint.setAntiAlias(true); // 畫筆反鋸齒

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
