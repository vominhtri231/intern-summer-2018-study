package asiantech.internship.summer.thachnguyen.debug;

        import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.LinearLayout;

        import asiantech.internship.summer.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private View view1, view2, view3;
    private LinearLayout ln3, ln4, ln5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        view1=findViewById(R.id.view1);
        view2=findViewById(R.id.view2);
        view3=findViewById(R.id.view3);

        ln4=findViewById(R.id.ll4);
        ln5=findViewById(R.id.ll5);
        ln3=findViewById(R.id.ll3);

        ln3.setOnClickListener(this);
        ln4.setOnClickListener(this);
        ln5.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.ll3:
                view1.setBackgroundColor(Color.parseColor("#FF35C611"));
                view2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                view3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case R.id.ll4:
                view1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                view2.setBackgroundColor(Color.parseColor("#FF35C611"));
                view3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
             case R.id.ll5:
                view1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                view2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                view3.setBackgroundColor(Color.parseColor("#FF35C611"));
                break;
        }
    }
}


