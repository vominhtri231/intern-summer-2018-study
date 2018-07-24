package asiantech.internship.summer.canvas;

import android.annotation.SuppressLint;
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
    private static final float MIN_ZOOM = 1f;
    private static final float MAX_ZOOM = 5f;

    private int[][] mData;
    private String[] mTypes;
    private int[] mColors;
    private String mCaption;

    private Rect mTextRect;
    private float scaleFactor = 1.f;
    private ScaleGestureDetector detector;
    private Paint mPaint;

    public WildlifeChartView(Context context) {
        this(context, null);
    }

    public WildlifeChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WildlifeChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        detector = new ScaleGestureDetector(getContext(), new ScaleListener());
        mTextRect = new Rect();
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.WildlifeChartView, defStyle, 0);
        int dataArrayReference = typedArray.getResourceId(R.styleable.WildlifeChartView_columnData, 0);
        if (dataArrayReference != 0) {
            final TypedArray dataTypedArray = getContext().getResources().obtainTypedArray(dataArrayReference);
            mData = new int[dataTypedArray.length()][];
            mTypes = new String[dataTypedArray.length()];
            for (int i = 0; i < dataTypedArray.length(); i++) {
                int dataReference = dataTypedArray.getResourceId(i, 0);
                String type = getContext().getResources().getResourceName(dataReference);
                type = type.substring(type.indexOf('/') + 1);
                mTypes[i] = type;
                int[] part = getContext().getResources().getIntArray(dataReference);
                mData[i] = part;
            }
            dataTypedArray.recycle();
        }
        int colorArrayReference = typedArray.getResourceId(R.styleable.WildlifeChartView_columnColor, 0);
        if (colorArrayReference != 0) {
            final TypedArray colorTypedArray = getContext().getResources().obtainTypedArray(colorArrayReference);
            mColors = new int[colorTypedArray.length()];
            for (int i = 0; i < colorTypedArray.length(); i++) {
                int colorReference = colorTypedArray.getResourceId(i, 0);
                int color = getContext().getResources().getColor(colorReference);
                mColors[i] = color;
            }
            colorTypedArray.recycle();
        }
        mCaption = typedArray.getString(R.styleable.WildlifeChartView_caption);
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());

        int numberLeft = 40;
        int captionTop = 100;
        int yearBottom = 100;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight - numberLeft;
        int contentHeight = getHeight() - paddingTop - paddingBottom - captionTop - yearBottom;

        int startX = paddingLeft + numberLeft;
        int startY = contentHeight + paddingTop + captionTop;

        int[] ruler = getRuler();
        int step = ruler[0];
        int maxStep = ruler[1];

        int stepHeight = (int) (contentHeight / (double) maxStep);
        double valueToHeight = stepHeight / (double) step;

        for (int i = 0; i <= maxStep; i++) {
            mPaint.setStrokeWidth(1);
            canvas.drawLine(startX, startY - i * stepHeight,
                    startX + contentWidth, startY - i * stepHeight, mPaint);
            String value = i * step + "";
            mPaint.getTextBounds(value, 0, value.length(), mTextRect);
            mPaint.setTextSize(30);
            mPaint.setStrokeWidth(5);
            canvas.drawText(value, startX / 2 - mTextRect.width() / 2,
                    startY - i * stepHeight + mTextRect.height() / 2, mPaint);
        }
        drawCaption(paddingTop, paddingLeft,
                paddingTop + captionTop, getWidth(), canvas);
        drawChart(startX, startY, valueToHeight, canvas);
        drawNode(startX, startY + 60, 30, canvas);
        canvas.restore();
    }

    private void drawCaption(int top, int left, int bottom, int right, Canvas canvas) {
        int captionBoxHeight = bottom - top;
        int captionBoxWidth = right - left;
        mPaint.setTextSize(40);
        mPaint.getTextBounds(mCaption, 0, mCaption.length(), mTextRect);
        canvas.drawText(mCaption, left + captionBoxWidth / 2 - mTextRect.width() / 2 - mTextRect.left,
                top + captionBoxHeight / 2 + mTextRect.height() / 2 - mTextRect.bottom, mPaint);
    }

    private void drawChart(int startX, int startY, double valueToHeight, Canvas canvas) {
        int year = 3000;
        int columnSize = 30;
        int farDistance = 60;
        int nearDistance = 10;
        int distanceBetweenYear = 2 * columnSize + farDistance + nearDistance;
        int distanceBetweenColumnInYear = columnSize + nearDistance;
        int lefty = 50;

        for (int i = 0; i < mData[0].length; i++) {
            mPaint.setColor(mColors[0]);
            int startYearX = startX + lefty + distanceBetweenYear * i;
            for (int j = 0; j < mData.length; j++) {
                mPaint.setColor(mColors[j]);
                canvas.drawRect(startYearX + distanceBetweenColumnInYear * j,
                        (int) (startY - valueToHeight * mData[j][i]),
                        startYearX + distanceBetweenColumnInYear * j + columnSize,
                        startY,
                        mPaint);
            }
            mPaint.setTextSize(20);
            mPaint.setColor(Color.BLACK);
            String printedYear = year + i * 5 + "";
            mPaint.getTextBounds(printedYear, 0, printedYear.length(), mTextRect);

            canvas.drawText(printedYear,
                    startYearX + columnSize + nearDistance / 2f - mTextRect.width() / 2f - mTextRect.left,
                    startY + 10 + mTextRect.height(),
                    mPaint);
        }
    }

    private void drawNode(int startX, int startY, int side, Canvas canvas) {
        int distanceType = 40;
        int distanceColorText = 10;
        int lastSize = 0;
        mPaint.setTextSize(30);
        for (int i = 0; i < mColors.length; i++) {
            int startOfIndexX = startX + i * (lastSize + distanceType);
            mPaint.setColor(mColors[i]);
            canvas.drawRect(startOfIndexX, startY, startOfIndexX + side, startY + side, mPaint);
            mPaint.setColor(Color.BLACK);
            canvas.drawText(mTypes[i], startOfIndexX + distanceColorText + side, startY + side, mPaint);
            mPaint.getTextBounds(mTypes[i], 0, mTypes[i].length(), mTextRect);
            lastSize = side + mTextRect.width() + distanceColorText;
        }
    }

    private int[] getRuler() {
        double max = 0;
        int step = 1;
        for (int[] row : mData)
            for (int value : row)
                if (value > max) {
                    max = value;
                }
        while (max > 100) {
            max /= 10;
            step *= 10;
        }
        while (max > 10) {
            max /= 2;
            step *= 2;
        }
        return new int[]{step, (int) Math.round(max)};
    }

    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent motionEvent) {
        detector.onTouchEvent(motionEvent);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(MIN_ZOOM, Math.min(scaleFactor, MAX_ZOOM));
            if (scaleFactor > 1f) {
                invalidate();
            }
            return true;
        }
    }
}
