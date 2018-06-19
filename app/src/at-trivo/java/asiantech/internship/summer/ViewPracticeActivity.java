package asiantech.internship.summer;


import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class ViewPracticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pracice);
        final ViewGroup rlTop=this.findViewById(R.id.rlTop);

        Glide.with(this)
                .load("https://www.gettyimages.ie/gi-resources/images/Homepage/Hero/UK/CMS_Creative_164657191_Kingfisher.jpg")
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        rlTop.setBackground(resource);
                    }
                });
    }

    private int[] eventViewIds=new int[]{R.id.eventView1,R.id.eventView2,R.id.eventView3};
    public void customSelector(View view){
        for(int eventId :eventViewIds){
            EventView eventView=findViewById(eventId);
            eventView.setClickColor(eventId==view.getId());
        }
    }
}
