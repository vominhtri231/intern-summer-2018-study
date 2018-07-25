package asiantech.internship.summer.canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import asiantech.internship.summer.canvas.model.Wildlife;

public class WildlifeChartView extends ScrollView {
    private ScaleGestureDetector mDetector;
//    private GestureDetector mGestureDetector;
    private Paint mPaint;
    private List<Wildlife> mWildlifeList;
    private float mScaleFactor = 1.f;
    private static final float MIN_ZOOM = 1f;
    private static final float MAX_ZOOM = 5f;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mMode;
    private float mStartX = 0f;
    private float mStartY = 0f;
    private float mTranslateX = 0f;
    private float mTranslateY = 0f;
    private float mPreviousTranslateX = 0f;
    private float mPreviousTranslateY = 0f;
    private boolean mDragged = false;

    public WildlifeChartView(Context context) {
        super(context);
        initPaint();
        mDetector = new ScaleGestureDetector(context, new ScaleListener());
        //mGestureDetector = new GestureDetector(context, new YScrollDetector());

        // mDisplay = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

    }

    public WildlifeChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        mDetector = new ScaleGestureDetector(context, new ScaleListener());
        //mGestureDetector = new GestureDetector(context, new YScrollDetector());

        // mDisplay = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    }

    public WildlifeChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        mDetector = new ScaleGestureDetector(context, new ScaleListener());
        //mGestureDetector = new GestureDetector(context, new YScrollDetector());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mMode = DRAG;
                mStartX = event.getX() - mPreviousTranslateX;
                mStartY = event.getY() - mPreviousTranslateY;
                break;
            case MotionEvent.ACTION_MOVE:
                mTranslateX = event.getX() - mStartX;
                mTranslateY = event.getY() - mStartY;
                double distance = Math.sqrt(Math.pow(event.getX() - (mStartX + mPreviousTranslateX), 2) +
                        Math.pow(event.getY() - (mStartY + mPreviousTranslateY), 2));
                if (distance > 0) {
                    mDragged = true;
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mMode = ZOOM;
                break;
            case MotionEvent.ACTION_UP:
                mMode = NONE;
                mDragged = false;
                mPreviousTranslateX = mTranslateX;
                mPreviousTranslateY = mTranslateY;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mMode = DRAG;
                mPreviousTranslateX = mTranslateX;
                mPreviousTranslateY = mTranslateY;
                break;
        }

        mDetector.onTouchEvent(event);
        if ((mMode == DRAG && mScaleFactor != 1f && mDragged) || mMode == ZOOM || mDragged) {
            invalidate();
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureDim(widthMeasureSpec, getWidth());
        int height = measureDim(heightMeasureSpec, getHeight());
        setMeasuredDimension(width, height);
    }

    private int measureDim(int measureSpec, int size) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = size;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);

        if ((mTranslateX * -1) < 0) {
            mTranslateX = 0;
        }
//        else if((mTranslateX * -1) > (mScaleFactor - 1) * getWidth()) {
//            mTranslateX = (1 - mScaleFactor) * getWidth();
//        }
        if (mTranslateY * -1 < 0) {
            mTranslateY = 0;
        } else if ((mTranslateY * -1) > (mScaleFactor - 1) * getHeight()) {
            mTranslateY = (1 - mScaleFactor) * getHeight();
        }

        canvas.translate(mTranslateX / mScaleFactor, mTranslateY / mScaleFactor);

        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(30);
        mPaint.setStyle(Paint.Style.FILL);
        float top = getHeight() * 2 / 3.0f;
        canvas.drawText("Wildlife Population", (getWidth() - 250) / 2.0f, top - 80 - 2*maxChartValue(mWildlifeList), mPaint);
        mPaint.setTextSize(20);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("Dolphins", 60, top + 70, mPaint);
        canvas.drawText("Whales", 200, top + 70, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(40, top + 55, 55, top + 70, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawRect(180, top + 55, 195, top + 70, mPaint);
        drawChart(canvas, top);
        canvas.restore();
    }

    private void initPaint() {
        createWildlifeList();
        setFadingEdgeLength(0);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(30);
    }

//    public void setWildlifeList(List<Wildlife> mWildlifeList) {
//        this.mWildlifeList = mWildlifeList;
//    }

    private void createWildlifeList() {
        mWildlifeList = new ArrayList<>();
        mWildlifeList.add(new Wildlife(40, 25));
        mWildlifeList.add(new Wildlife(68, 56));
        mWildlifeList.add(new Wildlife(70, 90));
        mWildlifeList.add(new Wildlife(88, 90));
        mWildlifeList.add(new Wildlife(80, 160));
        mWildlifeList.add(new Wildlife(98, 90));
        mWildlifeList.add(new Wildlife(78, 9));
        mWildlifeList.add(new Wildlife(80, 166));
        mWildlifeList.add(new Wildlife(70, 90));
        mWildlifeList.add(new Wildlife(40, 25));
        mWildlifeList.add(new Wildlife(80, 160));
        mWildlifeList.add(new Wildlife(70, 90));
        mWildlifeList.add(new Wildlife(98, 90));
        mWildlifeList.add(new Wildlife(78, 9));
        mWildlifeList.add(new Wildlife(80, 160));
        mWildlifeList.add(new Wildlife(70, 90));
        mWildlifeList.add(new Wildlife(40, 25));
    }

    private float maxChartValue(List<Wildlife> wildlifeList){
        List<Float> listChartValue=new ArrayList<>();
        for (Wildlife wildlife:wildlifeList) {
            listChartValue.add(wildlife.getmDolphin());
            listChartValue.add(wildlife.getmWhale());
        }
        return Collections.max(listChartValue);
    }

    private void drawChart(Canvas canvas, float top) {
        int year = 2017;
        int n = 0;
        float element=(getWidth()-110)/(13.0f*6);
        float left = 80+element*3;
        float totalWidth=left + 7*element * mWildlifeList.size() + 6*element * (mWildlifeList.size() - 1);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        if (totalWidth+30>getWidth()){
            while (n <= maxChartValue(mWildlifeList)+20) {
                canvas.drawText(String.valueOf(n), 40, top - n * 2, mPaint);
                canvas.drawLine(80, top - n * 2, totalWidth,top - n * 2, mPaint);
                n += 20;
            }
            canvas.drawRect(5, top - 140 - 2*maxChartValue(mWildlifeList), totalWidth + 30, top + 100, mPaint);
        }

        else {
            while (n <= 160) {
                canvas.drawText(String.valueOf(n), 40, top - n * 2, mPaint);
                canvas.drawLine(80, top - n * 2, getWidth()-30-3*element,top - n * 2, mPaint);
                n += 20;
            }
            canvas.drawRect(5, top - 140 - 2*maxChartValue(mWildlifeList), getWidth() - 5, top + 100, mPaint);
        }
        mPaint.setStyle(Paint.Style.FILL);
        for (Wildlife wildlife : mWildlifeList) {
            mPaint.setColor(Color.GRAY);
            mPaint.setTextSize(20);
            canvas.drawText(String.valueOf(year), left, top + 25, mPaint);
            mPaint.setColor(Color.BLUE);
            canvas.drawRect(left, top, left += element*3, top - wildlife.getmDolphin() * 2, mPaint);
            mPaint.setColor(Color.RED);
            canvas.drawRect(left += element, top, left += element*3, top - wildlife.getmWhale() * 2, mPaint);
            left += element*6;
            year++;
        }
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(MIN_ZOOM, Math.min(mScaleFactor, MAX_ZOOM));
            invalidate();
            return true;
        }

    }

//    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            return true;
//        }
//    }
}
