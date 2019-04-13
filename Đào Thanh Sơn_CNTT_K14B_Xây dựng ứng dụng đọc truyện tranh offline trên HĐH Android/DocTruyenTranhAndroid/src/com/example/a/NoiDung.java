package com.example.a;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class NoiDung extends ActionBarActivity {
	//final int[] DsHa = { R.drawable.bia_doreamon, R.drawable.bia_thandong };
	//ImageView imv;
	int width, height;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Display display = ((WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		width = display.getWidth();
		height = display.getHeight();

		this.setContentView(new MphotoView(this, width, height));
		// setContentView(R.layout.activity_main);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.gc();
		// finish();
	}

}
