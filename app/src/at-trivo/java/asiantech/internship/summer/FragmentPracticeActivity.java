package asiantech.internship.summer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class FragmentPracticeActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener{
    ArrayList<Account> mAccounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_practice);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.root,new LoginFragment());
        fragmentTransaction.commit();

        mAccounts=new ArrayList<Account>();
        mAccounts.add(new Account("a@gmail.com","123","thit an thit"));
        mAccounts.add(new Account("b@gmail.com","123","thit an ca"));
    }

    @Override
    public void login(String email, String password) {
        for(Account account :mAccounts){
            if(account.check(email,password)){
                break;
            }
        }
    }

    @Override
    public void signUp() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.root,SignUpFragment.newInstance());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
