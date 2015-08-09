package tw.com.skywing.whiteboard;

import tw.com.innolux.whiteboard.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init(); // 程式開始點

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private Painting painting;

	private ToggleButton[] penSize;

	private ToggleButton[] penColor;

	private ToggleButton eraser;

	private Button clear;

	private void init() {

		DisplayMetrics displayMetrics = new DisplayMetrics();

		this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

		int disWidth = displayMetrics.widthPixels; // 取得螢幕寬度

		int disHeigth = displayMetrics.heightPixels; // 取得螢幕高度

		painting = (Painting) this.findViewById(R.id.painting1);

		painting.setCanvasSize(disWidth, disHeigth - 50); // 設定畫布使用bitmap尺寸

		penSize = new ToggleButton[3];

		penSize[0] = (ToggleButton) this.findViewById(R.id.penSizeButton1);

		penSize[1] = (ToggleButton) this.findViewById(R.id.penSizeButton2);

		penSize[2] = (ToggleButton) this.findViewById(R.id.penSizeButton3);

		for (ToggleButton tb : penSize)

			tb.setOnClickListener(penModeClickListener);

		penColor = new ToggleButton[4];

		penColor[0] = (ToggleButton) this.findViewById(R.id.colorButton1);

		penColor[1] = (ToggleButton) this.findViewById(R.id.colorButton2);

		penColor[2] = (ToggleButton) this.findViewById(R.id.colorButton3);

		penColor[3] = (ToggleButton) this.findViewById(R.id.colorButton4);

		for (ToggleButton tb : penColor)

			tb.setOnClickListener(penColorClickLisener);

		clear = (Button) this.findViewById(R.id.clearbutton1);

		clear.setOnClickListener(clearClickListener);

		eraser = (ToggleButton) this.findViewById(R.id.eraserButton1);

		eraser.setOnClickListener(penModeClickListener);

	}

	OnClickListener penModeClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			for (ToggleButton tb : penSize)

				tb.setChecked(false);

			eraser.setChecked(false);

			painting.setPaintMode("isDraw");

			switch (v.getId()) {

			case R.id.penSizeButton1:

				penSize[0].setChecked(true);

				painting.changePenSize(1);

				break;

			case R.id.penSizeButton2:

				penSize[1].setChecked(true);

				painting.changePenSize(2);

				break;

			case R.id.penSizeButton3:

				penSize[2].setChecked(true);

				painting.changePenSize(3);

				break;

			case R.id.eraserButton1:

				eraser.setChecked(true);

				painting.setPaintMode("isEraser");

				break;

			}

		}
	};

	OnClickListener penColorClickLisener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			for (ToggleButton tb : penColor)

				tb.setChecked(false);

			painting.setPaintMode("isDraw");

			switch (v.getId()) {

			case R.id.colorButton1:

				painting.changePenColor(1);

				penColor[0].setChecked(true);

				break;

			case R.id.colorButton2:

				painting.changePenColor(2);

				penColor[1].setChecked(true);

				break;

			case R.id.colorButton3:

				painting.changePenColor(3);

				penColor[2].setChecked(true);

				break;

			case R.id.colorButton4:

				painting.changePenColor(4);

				penColor[3].setChecked(true);

				break;

			}

		}
	};

	OnClickListener clearClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			painting.canvasClear();

		}
	};

}
