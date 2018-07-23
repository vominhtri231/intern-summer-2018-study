package asiantech.internship.summer.canvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import asiantech.internship.summer.R;

public class WildlifeChartView extends View {

    int[][] mData;
    Rect textRect = new Rect();
    private static float MIN_ZOOM = 1f;
    private static float MAX_ZOOM = 5f;
    private float scaleFactor = 1.f;
    private ScaleGestureDetector detector;

    Paint mPaint;

    public ScaleGestureDetector getDetector(){
        if(detector==null){
            detector=new ScaleGestureDetector(getContext(),new ScaleListener());
        }
        return detector;
    }

    public WildlifeChartView(Context context) {
        super(context);
        init(null, 0);
    }

    public WildlifeChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public WildlifeChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.WildlifeChartView, defStyle, 0);

        int dataArrayReference = typedArray.getResourceId(R.styleable.WildlifeChartView_dolphin, 0);
        if (dataArrayReference != 0) {
            final TypedArray dataTypedArray = getContext().getResources().obtainTypedArray(dataArrayReference);
            mData = new int[dataTypedArray.length()][];
            for (int i = 0; i < dataTypedArray.length(); i++) {
                int dataReference = dataTypedArray.getResourceId(i, 0);
                int[] part = getContext().getResources().getIntArray(dataReference);
                mData[i] = part;
            }
            dataTypedArray.recycle();
        }

        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scaleFactor, scaleFactor);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop() + 100;
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom() + 100;

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;


        int startX = paddingLeft;
        int startY = contentHeight + paddingTop;

        canvas.drawLine(startX, startY, startX + contentWidth, startY, mPaint);
        canvas.drawLine(startX, startY, startX, paddingTop, mPaint);
        drawCaption("it's a chart", 0, 0, paddingTop, getWidth(), canvas);

        int year = 3000;
        int columnSize = 30;
        int farDistance = 60;
        int nearDistance = 10;
        int distanceBetweenYear = 2 * columnSize + farDistance + nearDistance;
        int distanceBetweenColumnInYear = columnSize + nearDistance;
        int lefty = 50;

        for (int i = 0; i < mData[0].length; i++) {
            mPaint.setColor(Color.RED);
            int startYearX = startX + lefty + distanceBetweenYear * i;
            canvas.drawRect(startYearX,
                    startY - 100 * mData[0][i],
                    startYearX + columnSize,
                    startY,
                    mPaint);
            mPaint.setColor(Color.BLUE);
            canvas.drawRect(startYearX + distanceBetweenColumnInYear,
                    startY - 100 * mData[1][i],
                    startYearX + distanceBetweenColumnInYear + columnSize,
                    startY,
                    mPaint);
            mPaint.setTextSize(20);
            mPaint.setColor(Color.BLACK);
            String printedYear = year + i * 5 + "";
            mPaint.getTextBounds(printedYear, 0, printedYear.length(), textRect);

            canvas.drawText(printedYear,
                    startYearX + columnSize + nearDistance / 2f - textRect.width() / 2f - textRect.left,
                    startY + 10 + textRect.height(),
                    mPaint);
        }
        canvas.restore();

    }

    private void drawCaption(String caption, int top, int left, int bottom, int right, Canvas canvas) {
        int captionBoxHeight = bottom - top;
        int captionBoxWidth = right - left;
        mPaint.setTextSize(40);
        mPaint.getTextBounds(caption, 0, caption.length(), textRect);
        canvas.drawText(caption, left + captionBoxWidth / 2 - textRect.width() / 2 - textRect.left,
                top + captionBoxHeight / 2 + textRect.height() / 2 - textRect.bottom, mPaint);

    }

    public boolean onTouchEvent(MotionEvent motionEvent){
        getDetector().onTouchEvent(motionEvent);
        return true;
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(MIN_ZOOM, Math.min(scaleFactor, MAX_ZOOM));
            invalidate();
            return true;
        }
    }

}
