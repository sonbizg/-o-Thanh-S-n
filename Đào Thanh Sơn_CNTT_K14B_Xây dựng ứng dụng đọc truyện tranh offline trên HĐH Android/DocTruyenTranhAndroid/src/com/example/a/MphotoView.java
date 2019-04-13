package com.example.a;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MphotoView extends View {
	private static int DEFAULT_FLIP_VALUE = 20;
	private static int FLIP_SPEED = 30;
	private long mMoveDelay = 1000 / 30;
	float xTouchValue = DEFAULT_FLIP_VALUE, yTouchValue = DEFAULT_FLIP_VALUE;

	class FlippingHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			Log.i("Thong bao: ", "Clock Handler is still running");
			MphotoView.this.flip();
		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	}

	FlippingHandler flippingHandler;

	private Bitmap page1;
	private Bitmap page2;

	private ArrayList<Bitmap> pages = new ArrayList<Bitmap>();
	int curindex = 0;
	boolean isnext = true;
	int totalphoto;

	int width;
	int height;

	float oldTouchX, oldTouchY;
	boolean flipping = false;
	boolean next;

	Point A, B, C, D, E, F;

	Bitmap visiblePage;
	Bitmap invisiblePage;
	Paint flipPagePaint;

	boolean flip = false;

	Context context;

	int loadedPages = 0;
	long timeToLoad = 0;

	boolean onloading = true;
	boolean onMoving = false;

	// constructor
	public MphotoView(Context context) {
		super(context);
		this.context = context;

		init();
		setFocusable(true);
		setFocusableInTouchMode(true);
	}

	public MphotoView(Context context, int width, int height) {
		super(context);
		this.context = context;
		this.width = width;
		pages = new ArrayList<Bitmap>();
		this.height = height;
		switch (MainActivity.tentruyen) {

		case "Mèo Máy Doreamon":
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore001));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore002));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore003));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore004));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore005));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore006));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore007));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore008));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore009));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore010));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore011));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore012));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore013));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore014));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore015));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore016));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore017));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore018));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore019));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.dore020));
			break;
		case "Thần Đồng Đất Việt":
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong001));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong002));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong003));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong004));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong005));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong006));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong007));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong008));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong009));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong010));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong011));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong012));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong013));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong014));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong015));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong016));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong017));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong018));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong019));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.thandong020));
			break;

		case "7 Viên Ngọc Rồng":
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku001));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku002));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku003));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku004));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku005));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku006));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku007));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku008));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku009));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku010));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku011));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku012));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku013));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku014));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku015));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku016));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku017));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku018));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku019));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.songoku020));
			break;
		case "Thám Tử Lừng Danh Conan":
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan001));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan002));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan003));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan004));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan005));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan006));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan007));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan008));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan009));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan010));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan011));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan012));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan013));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan014));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan015));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan016));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan017));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan018));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan019));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.conan020));
			break;
		case "Đội Quân Nhí Nhố":
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan001));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan002));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan003));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan004));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan005));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan006));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan007));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan008));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan009));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan010));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan011));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan012));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan013));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan014));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan015));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan016));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan017));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan018));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan019));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.doiquan020));
			break;
		case "Ô Long Viện":
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong001));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong002));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong003));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong004));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong005));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong006));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong007));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong008));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong009));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong010));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong011));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong012));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong013));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong014));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong015));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong016));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong017));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong018));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong019));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.olong020));

			break;
		case "Siêu Quậy Teppi":
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi001));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi002));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi003));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi004));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi005));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi006));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi007));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi008));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi009));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi010));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi011));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi012));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi013));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi014));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi015));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi016));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi017));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi018));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi019));
			pages.add(BitmapFactory.decodeResource(context.getResources(),
					R.drawable.teppi020));
			break;
		}

		page1 = pages.get(0);
		page2 = pages.get(1);

		totalphoto = pages.size() - 1;

		init();
	}

	private void init() {
		flippingHandler = new FlippingHandler();
		flipPagePaint = new Paint();
		flipPagePaint.setColor(Color.rgb(180, 180, 180));
		flipPagePaint.setShadowLayer(5, -5, 5, 0x99000000);
		A = new Point(10, 0);
		B = new Point(width, height);
		C = new Point(width, 0);
		D = new Point(0, 0);
		E = new Point(0, 0);
		F = new Point(0, 0);

		xTouchValue = yTouchValue = DEFAULT_FLIP_VALUE;
		visiblePage = page1;
		invisiblePage = page2;
		onMoving = false;
		flipping = false;

		loadData();
	}

	private void loadData() {
		onloading = false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (!onloading) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				oldTouchX = event.getX();
				oldTouchY = event.getY();
				flip = true;
				if (oldTouchX > (width >> 1)) {
					xTouchValue = DEFAULT_FLIP_VALUE;
					yTouchValue = DEFAULT_FLIP_VALUE;
					// set invisible page's content

					next = true;

					if (isnext) {
						if (curindex == totalphoto)
							curindex = 0;
						else
							curindex++;
					}
					isnext = true;

				} else {
					// set invisible page's content

					next = false;

					if (isnext == false) {
						if (curindex == 0)
							curindex = totalphoto;
						else
							curindex--;
					}
					isnext = false;

					swap2Page();
					xTouchValue = width;
					yTouchValue = DEFAULT_FLIP_VALUE;
				}
				break;
			case MotionEvent.ACTION_UP:
				if (onMoving) {
					xTouchValue = width - A.x;
					onMoving = false;
				}
				flipping = true;
				flip();
				break;
			case MotionEvent.ACTION_MOVE:
				onMoving = true;

				float xMouse = event.getX();
				float yMouse = event.getY();

				xTouchValue -= (xMouse - oldTouchX) / 1;

				yTouchValue -= yMouse - oldTouchY;

				if (xMouse < oldTouchX) {
					if (!next) {
						flip = false;
					}
					next = true;

				} else {
					if (next) {
						flip = false;
					}
					next = false;
				}

				oldTouchX = event.getX();
				oldTouchY = event.getY();

				this.invalidate();
				break;
			}

		}

		return true;
	}

	public void flip() {
		// neu chua load thi load va lat sang trang
		if (flipping) {
			if (xTouchValue > width || xTouchValue < DEFAULT_FLIP_VALUE) {
				flipping = false;
				if (!flipping) {
					if (next) {
						swap2Page();
					}
					flip = false;
					xTouchValue = DEFAULT_FLIP_VALUE;
					yTouchValue = DEFAULT_FLIP_VALUE;
				}
				return;
			}
			if (next) {
				// new cd sang trai-> trang moi
				xTouchValue += FLIP_SPEED;
			} else {
				// neu cd sang phai-> trang cu
				xTouchValue -= FLIP_SPEED;
			}
			this.invalidate();

			// call hander
			flippingHandler.sleep(mMoveDelay);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// super.onDraw(canvas);
		width = getWidth();
		height = getHeight();

		if (flipping) {
			pointGenerate(xTouchValue, width, height);
		} else {
			pointGenerate(xTouchValue, width, height);
		}

		// First Page render
		Paint paint = new Paint();
		canvas.drawColor(Color.rgb(220, 220, 220));
		int fromOX = (width - visiblePage.getWidth()) / 2;
		int fromOY = (height - visiblePage.getHeight()) / 2;
		if (fromOX > 0 && fromOY > 0)
			canvas.drawBitmap(visiblePage, Math.abs(fromOX), Math.abs(fromOY),
					paint);
		else if (fromOX > 0)
			canvas.drawBitmap(visiblePage, Math.abs(fromOX), 0, paint);
		else if (fromOY > 0)
			canvas.drawBitmap(visiblePage, 0, Math.abs(fromOY), paint);
		else
			canvas.drawBitmap(visiblePage, 0, 0, paint);

		// Second Page Render
		Path pathX = pathOfTheMask();
		canvas.clipPath(pathX);
		int fromOX_ = (width - invisiblePage.getWidth()) / 2;
		int fromOY_ = (height - invisiblePage.getHeight()) / 2;
		if (fromOX_ > 0 && fromOY_ > 0)
			canvas.drawBitmap(invisiblePage, Math.abs(fromOX_),
					Math.abs(fromOY_), paint);
		else if (fromOX > 0)
			canvas.drawBitmap(invisiblePage, Math.abs(fromOX_), 0, paint);
		else if (fromOY > 0)
			canvas.drawBitmap(invisiblePage, 0, Math.abs(fromOY_), paint);
		else
			canvas.drawBitmap(invisiblePage, 0, 0, paint);

		canvas.restore();
		// Flip Page render

		Path pathX2 = pathOfFlippedPaper();
		canvas.drawPath(pathX2, flipPagePaint);

		pathX = null;
		pathX2 = null;
		paint = null;

	}

	private Path pathOfTheMask() {
		Path path = new Path();
		path.moveTo(A.x, A.y);
		path.lineTo(B.x, B.y);
		path.lineTo(C.x, C.y);
		path.lineTo(D.x, D.y);
		path.lineTo(A.x, A.y);

		return path;
	}

	private Path pathOfFlippedPaper() {
		Path path = new Path();
		path.moveTo(A.x, A.y);
		path.lineTo(D.x, D.y);
		path.lineTo(E.x, E.y);
		path.lineTo(F.x, F.y);
		path.lineTo(A.x, A.y);
		return path;
	}

	private void pointGenerate(float distance, int width, int height) {
		float xA = width - distance;
		float yA = height;

		// float xB= width;
		// float yB= height;

		// float xC = width;
		// float yC = 0;

		float xD = 0;
		float yD = 0;

		if (xA > width / 2) {
			xD = width;
			yD = height - (width - xA) * height / xA;
		} else {
			xD = 2 * xA;
			yD = 0;
		}

		double a = (height - yD) / (xD + distance - width);
		double alpha = Math.atan(a);
		double _cos = Math.cos(2 * alpha), _sin = Math.sin(2 * alpha);
		// E
		float xE = (float) (xD + _cos * (width - xD));
		float yE = (float) -(_sin * (width - xD));
		// F
		float xF = (float) (width - distance + _cos * distance);
		float yF = (float) (height - _sin * distance);

		if (xA > width / 2) {
			xE = xD;
			yE = yD;
		}

		A.x = xA;
		A.y = yA;
		D.x = xD;
		D.y = yD;
		E.x = xE;
		E.y = yE;
		F.x = xF;
		F.y = yF;

	}

	float oldxF = 0, oldyF = 0;

	private void pointGenerateII(float xTouch, float yTouch, int width,
			int height) {
		float yA = height;
		float xD = width;

		float xF = width - xTouch + 0.1f;
		float yF = height - yTouch + 0.1f;

		if (A.x == 0) {
			xF = Math.min(xF, oldxF);
			yF = Math.max(yF, oldyF);
		}

		float deltaX = width - xF;
		float deltaY = height - yF;

		float BH = (float) (Math.sqrt(deltaX * deltaX + deltaY * deltaY) / 2);
		double tangAlpha = deltaY / deltaX;
		double alpha = Math.atan(tangAlpha);
		double _cos = Math.cos(alpha), _sin = Math.sin(alpha);

		float xA = (float) (width - (BH / _cos));
		float yD = (float) (height - (BH / _sin));

		xA = Math.max(0, xA);
		if (xA == 0) {
			// yF= Math.max(yF, height-(float) Math.sqrt(width*width-xF*xF));
			oldxF = xF;
			oldyF = yF;
		}

		float xE = xD;
		float yE = yD;
		// if (xA > width / 2)

		if (yD < 0) {
			xD = width + (float) (tangAlpha * yD);
			yE = 0;
			xE = width + (float) (Math.tan(2 * alpha) * yD);

		}

		A.x = xA;
		A.y = yA;
		D.x = xD;
		D.y = Math.max(0, yD);
		E.x = xE;
		E.y = yE;
		F.x = xF;
		F.y = yF;
	}

	private void swap2Page() {
		if (isnext == true) {
			if (curindex == totalphoto) {
				visiblePage = pages.get(curindex);
				invisiblePage = pages.get(0);
			} else {
				visiblePage = pages.get(curindex);
				invisiblePage = pages.get(curindex + 1);
			}
		} else {
			if (curindex == 0) {
				visiblePage = pages.get(totalphoto);
				invisiblePage = pages.get(curindex);
			} else {
				visiblePage = pages.get(curindex - 1);
				invisiblePage = pages.get(curindex);
			}
		}

	}
}