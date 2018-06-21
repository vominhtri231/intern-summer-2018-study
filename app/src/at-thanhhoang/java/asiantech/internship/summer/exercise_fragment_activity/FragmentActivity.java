package asiantech.internship.summer.exercise_fragment_activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import asiantech.internship.summer.R;

public class FragmentActivity extends AppCompatActivity {

    public static final String TITLE_LOGIN = "Fragment Log In";
    public static final String TITLE_SIGNUP = "Fragment Sign Up";

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        toolbar = findViewById(R.id.toolbar);

        FragmentLogin mFragmentLogin = new FragmentLogin();

        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, mFragmentLogin);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public void setTitleToolbar(String title){
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }
}


