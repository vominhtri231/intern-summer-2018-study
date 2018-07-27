package asiantech.internship.summer.canvas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import asiantech.internship.summer.canvas.model.Wildlife;

public class WildlifeChartView extends View {
    private final ScaleGestureDetector mDetector;
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
    private float mTotalWidth = 0;

    public WildlifeChartView(Context context) {
        super(context);
        initPaint();
        mDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    public WildlifeChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        mDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    public WildlifeChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        mDetector = new ScaleGestureDetector(context, new ScaleListener());
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
        if (mMode == ZOOM) {
            mDetector.onTouchEvent(event);
        }
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
        canvas.scale(mScaleFactor, mScaleFactor, mDetector.getFocusX(), mDetector.getFocusY());
        if ((mTranslateX * -1) < 0) {
            mTranslateX = 0;
        }
        if (-mTranslateX >= mTotalWidth - getWidth() + 80) {
            mTranslateX = -(mTotalWidth - getWidth() + 80);
        }
        if (mTranslateY * -1 < 0) {
            mTranslateY = 0;
        } else if ((mTranslateY * -1) > (mScaleFactor - 1) * getHeight()) {
            mTranslateY = (1 - mScaleFactor) * getHeight();
        }
        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(30);
        mPaint.setStyle(Paint.Style.FILL);
        float top = getHeight() * 2 / 3.0f;
        canvas.drawText("Wildlife Population", (getWidth() - 250) / 2.0f, top - 80 - 2 * maxChartValue(mWildlifeList), mPaint);
        mPaint.setTextSize(20);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("Dolphins", 60, top + 70, mPaint);
        canvas.drawText("Whales", 200, top + 70, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(40, top + 55, 55, top + 70, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawRect(180, top + 55, 195, top + 70, mPaint);
        canvas.translate(mTranslateX, 0);
        drawChart(canvas, top);
        canvas.restore();
        canvas.scale(mScaleFactor, mScaleFactor, mDetector.getFocusX(), mDetector.getFocusY());
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(getWidth() - 5, top - 140 - 2 * maxChartValue(mWildlifeList), getWidth(), top + 50, mPaint);
        canvas.drawRect(0, top - 140 - 2 * maxChartValue(mWildlifeList), 80, top + 50, mPaint);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(5, top - 140 - 2 * maxChartValue(mWildlifeList), getWidth() - 5, top + 100, mPaint);
        drawLine(canvas, top);
    }

    @SuppressWarnings("deprecation")
    private void initPaint() {
        createWildlifeList();
        WindowManager w = ((Activity) getContext()).getWindowManager();
        float element = (w.getDefaultDisplay().getWidth() - 110) / (13.0f * 6);
        mTotalWidth = 80 + 7 * element * mWildlifeList.size() + 6 * element * (mWildlifeList.size() - 1);
        setFadingEdgeLength(0);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(30);
    }

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

    private float maxChartValue(List<Wildlife> wildlifeList) {
        List<Float> listChartValue = new ArrayList<>();
        for (Wildlife wildlife : wildlifeList) {
            listChartValue.add(wildlife.getmDolphin());
            listChartValue.add(wildlife.getmWhale());
        }
        return Collections.max(listChartValue);
    }

    private void drawLine(Canvas canvas, float top) {
        int n = 0;
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GRAY);
        while (n <= maxChartValue(mWildlifeList) + 20) {
            canvas.drawText(String.valueOf(n), 40, top - n * 2, mPaint);
            n += 20;
        }
    }

    private void drawChart(Canvas canvas, float top) {
        int year = 2017;
        float element = (getWidth() - 110) / (13.0f * 6);
        float left = 80 + element * 3;
        int n = 0;
        mPaint.setStyle(Paint.Style.FILL);
        float totalWidth = left + 7 * element * mWildlifeList.size() + 6 * element * (mWildlifeList.size() - 1);
        mPaint.setColor(Color.GRAY);
        if (totalWidth + 30 > getWidth()) {
            while (n <= maxChartValue(mWildlifeList) + 20) {
                canvas.drawLine(80, top - n * 2, totalWidth, top - n * 2, mPaint);
                n += 20;
            }
        } else {
            while (n <= 160) {
                canvas.drawLine(80, top - n * 2, getWidth() - 30 - 3 * element, top - n * 2, mPaint);
                n += 20;
            }
        }
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
        for (Wildlife wildlife : mWildlifeList) {
            mPaint.setColor(Color.GRAY);
            mPaint.setTextSize(20);
            canvas.drawText(String.valueOf(year), left, top + 25, mPaint);
            mPaint.setColor(Color.BLUE);
            canvas.drawRect(left, top, left += element * 3, top - wildlife.getmDolphin() * 2, mPaint);
            mPaint.setColor(Color.RED);
            canvas.drawRect(left += element, top, left += element * 3, top - wildlife.getmWhale() * 2, mPaint);
            left += element * 6;
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
            if (mScaleFactor > 1f) {
                invalidate();
            }
            return true;
        }
    }
}
