package asiantech.internship.summer;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DayView extends RelativeLayout {
    View root;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public DayView(Context context) {
        super(context);
        init(context,null);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public DayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public DayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void init(Context context, AttributeSet attrs){
        root=inflate(context,R.layout.custome_day,this);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.DayView);

        TextView tvDay=root.findViewById(R.id.tvDay);
        String dayInput=a.getString(R.styleable.DayView_day);
        tvDay.setText(dayInput);

        TextView tvDayNumber=root.findViewById(R.id.tvDayNumber);
        String dayNumberInput=a.getString(R.styleable.DayView_dayNumber);
        tvDayNumber.setText(dayNumberInput);


        boolean isToday=a.getBoolean(R.styleable.DayView_isToday,false);
        if(isToday) {tvDayNumber.setBackground(getResources().getDrawable(R.drawable.day_circle));}

        boolean isHasEvent=a.getBoolean(R.styleable.DayView_isHasEvent,false);
        if(isHasEvent){
            ImageView imgHasMessage=root.findViewById(R.id.imgHasMessage);
            imgHasMessage.setBackground(getResources().getDrawable(R.drawable.has_message_circle));
        }
        a.recycle();

    }


}
