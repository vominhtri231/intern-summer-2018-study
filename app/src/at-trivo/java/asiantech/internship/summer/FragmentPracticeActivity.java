package asiantech.internship.summer;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class FragmentPracticeActivity extends AppCompatActivity
        implements LoginFragment.OnFragmentInteractionListener,
        SignUpFragment.OnFragmentInteractionListener {

    ArrayList<Account> mAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_practice);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.root, new LoginFragment());
        fragmentTransaction.commit();

        mAccounts = new ArrayList<>();
        mAccounts.add(new Account("a@gmail.com", "12345678", "thit an thit"));
        mAccounts.add(new Account("b@gmail.com", "12345678", "thit an ca"));
    }

    @Override
    public boolean login(String email, String password) {
        boolean isHasAccount = false;
        for (Account account : mAccounts) {
            if (account.check(email, password)) {
                isHasAccount = true;
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);
                InfoFragment infoFragment = new InfoFragment();
                Bundle bundle = new Bundle();
                bundle.putString(InfoFragment.HELLO_KEY, email);
                infoFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.root, infoFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            }
        }
        return isHasAccount;
    }

    @Override
    public void signUp() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.root, new SignUpFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void addAccount(String email, String password) {
        mAccounts.add(new Account(email, password, "new user"));

        Bundle bundle = new Bundle();
        bundle.putString(LoginFragment.DEFAULT_EMAIL_KEY, email);
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.root, loginFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
