package tw.com.skywing.whiteboard;

import android.graphics.Color;
import android.graphics.Paint;

public class Eraser {

	private Paint eraserPaint, ePaint;

	public Eraser() {

		eraserPaint = new Paint();

		eraserPaint.setColor(Color.WHITE);

		eraserPaint.setDither(true); // �p�ٮ�������

		eraserPaint.setAntiAlias(true); // ���P����X

		ePaint = new Paint();

		ePaint.setStrokeWidth(2);

		ePaint.setColor(0xffD8D8D8); // ��Ƥ�������ɫ

		ePaint.setStyle(Paint.Style.STROKE);

		ePaint.setAntiAlias(true); // ���P����X

	}

	public Paint getEraserPaint() {

		return eraserPaint;

	}

	public Paint getEPaint() {

		return ePaint;

	}

}
