package asiantech.internship.summer.exercise_fragment_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import asiantech.internship.summer.R;

public class ResultActivity extends AppCompatActivity{

    private TextView tvMail, tvPassword;
    private Toolbar toolbarResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initView();

        toolbarResult.setTitle("Result Activity");
        setSupportActionBar(toolbarResult);

        Intent intent = getIntent();

        String email_show = intent.getStringExtra(LoginFragment.KEY_MAIL);
        String pass_show = intent.getStringExtra(LoginFragment.KEY_PASS);

        setData(email_show,pass_show);

    }

    private void setData(String email_show, String pass_show) {
        tvMail.setText(email_show);
        tvPassword.setText(pass_show);
    }

    private void initView() {
        tvMail = findViewById(R.id.tvEmailShow);
        tvPassword = findViewById(R.id.tvPasswordShow);

        toolbarResult = findViewById(R.id.toolbarResult);
    }
}

