package asiantech.internship.summer.exercise_fragment_activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import asiantech.internship.summer.R;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentLogin mFragmentLogin = new FragmentLogin();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, mFragmentLogin);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}


