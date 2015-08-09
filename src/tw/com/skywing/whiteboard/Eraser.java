package tw.com.skywing.whiteboard;

import android.graphics.Color;
import android.graphics.Paint;

public class Eraser {

	private Paint eraserPaint, ePaint;

	public Eraser() {

		eraserPaint = new Paint();

		eraserPaint.setColor(Color.WHITE);

		eraserPaint.setDither(true); // 減少畫布抖動

		eraserPaint.setAntiAlias(true); // 畫筆反鋸齒

		ePaint = new Paint();

		ePaint.setStrokeWidth(2);

		ePaint.setColor(0xffD8D8D8); // 橡皮擦範圍顏色

		ePaint.setStyle(Paint.Style.STROKE);

		ePaint.setAntiAlias(true); // 畫筆反鋸齒

	}

	public Paint getEraserPaint() {

		return eraserPaint;

	}

	public Paint getEPaint() {

		return ePaint;

	}

}
