package asiantech.internship.summer.view_and_viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import asiantech.internship.summer.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class EventView extends RelativeLayout {

    private View mRoot;

    public EventView(Context context) {
        super(context);
        init(context, null);
    }

    public EventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EventView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        mRoot = inflate(context, R.layout.custome_event, this);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.EventView);

        String imageLink = a.getString(R.styleable.EventView_image_src);
        CircleImageView imgProfile = mRoot.findViewById(R.id.imgProfile);
        Glide.with(this)
                .load(imageLink)
                .apply(new RequestOptions().override(100, 100).centerCrop())
                .into(imgProfile);

        String messageInput = a.getString(R.styleable.EventView_message);
        TextView tvMessage = mRoot.findViewById(R.id.tvMessage);
        tvMessage.setText(messageInput);

        String timeInput = a.getString(R.styleable.EventView_time);
        TextView tvTime = mRoot.findViewById(R.id.tvTime);
        tvTime.setText(timeInput);

        String locationInput = a.getString(R.styleable.EventView_location);
        TextView location = mRoot.findViewById(R.id.tvLocation);
        location.setText(locationInput);

        a.recycle();
    }

    public void setClickColor(boolean isClick) {
        ImageView status = mRoot.findViewById(R.id.imgStatus);
        int colorIndex = isClick ? R.color.colorEventSelected : R.color.colorEventUnSelected;
        status.setBackgroundColor(getResources().getColor(colorIndex));
    }
}
