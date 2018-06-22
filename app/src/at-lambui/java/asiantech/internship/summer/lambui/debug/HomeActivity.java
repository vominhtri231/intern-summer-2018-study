package asiantech.internship.summer.lambui.debug;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.Objects;
import asiantech.internship.summer.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Home");
        LogInFragment logInFragment = new LogInFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_result, logInFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
