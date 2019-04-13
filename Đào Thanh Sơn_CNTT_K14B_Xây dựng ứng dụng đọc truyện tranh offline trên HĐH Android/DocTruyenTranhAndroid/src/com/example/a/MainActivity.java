package com.example.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	ListView lvDanhSach;

	public static int VT = 0;
	public static String tentruyen = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		lvDanhSach = (ListView) findViewById(R.id.listView1);

		final String DS[] = { "Ô Long Viện", "Mèo Máy Doreamon",
				"Thần Đồng Đất Việt", "7 Viên Ngọc Rồng",
				"Thám Tử Lừng Danh Conan", "Đội Quân Nhí Nhố",
				"Siêu Quậy Teppi" };

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, DS);
		Display display = ((WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		// this.setContentView(new NoiDung(this, width, height));
		lvDanhSach.setAdapter(adapter);
		lvDanhSach
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int vitri, long arg3) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), DS[vitri],
								Toast.LENGTH_SHORT).show();
						VT = vitri;
						tentruyen = DS[vitri];
						Intent i = new Intent(MainActivity.this, NoiDung.class);
						startActivity(i);
					}
				});

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.gc();
		// finish();
	}
}
