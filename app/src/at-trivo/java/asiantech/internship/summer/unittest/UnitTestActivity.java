package asiantech.internship.summer.unittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import asiantech.internship.summer.R;
import asiantech.internship.summer.unittest.helpers.Validator;
import asiantech.internship.summer.unittest.model.User;

public class UnitTestActivity extends AppCompatActivity {

    private TextView mTvNotification;
    private EditText mEdtUsername;
    private EditText mEdtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_test);
        initView();
        setUpLogin();
    }

    private void initView() {
        mTvNotification = findViewById(R.id.tvNotification);
        mEdtUsername = findViewById(R.id.edtUsername);
        mEdtPassword = findViewById(R.id.edtPassword);
    }

    private void setUpLogin() {
        Button mBttLogin = findViewById(R.id.bttLogin);
        mBttLogin.setOnClickListener(view -> {
            String username = mEdtUsername.getText().toString();
            String password = mEdtPassword.getText().toString();
            mTvNotification.setText(Validator.validate(new User(username, password)));
        });
    }
}
