package asiantech.internship.summer.view_and_viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import asiantech.internship.summer.R;

public class DayView extends RelativeLayout {

    public DayView(Context context) {
        this(context, null);
    }

    public DayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View rootView = inflate(context, R.layout.custome_day, this);
        TextView tvDay = rootView.findViewById(R.id.tvDay);
        TextView tvDayNumber = rootView.findViewById(R.id.tvDayNumber);
        ImageView imgHasMessage = rootView.findViewById(R.id.imgHasMessage);
        final TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.DayView);

        try {
            String dayInput = typedArray.getString(R.styleable.DayView_day);
            String dayNumberInput = typedArray.getString(R.styleable.DayView_dayNumber);
            boolean isToday = typedArray.getBoolean(R.styleable.DayView_isToday, false);
            boolean isHasEvent = typedArray.getBoolean(R.styleable.DayView_isHasEvent, false);

            tvDay.setText(dayInput);
            tvDayNumber.setText(dayNumberInput);
            if (isToday) {
                tvDayNumber.setBackground(getResources().getDrawable(R.drawable.shape_circle_day));
            }
            if (isHasEvent) {
                imgHasMessage.setBackground(getResources().getDrawable(R.drawable.shape_circle_has_message));
            }
        } finally {
            typedArray.recycle();
        }
    }

}
