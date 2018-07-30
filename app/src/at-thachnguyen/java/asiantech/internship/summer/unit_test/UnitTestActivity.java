package asiantech.internship.summer.unit_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import asiantech.internship.summer.R;

public class UnitTestActivity extends AppCompatActivity {
    private EditText mEdtUsername;
  //  private EditText mEdtPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        initView();
        mBtnLogin.setOnClickListener(v -> {
            if (!CheckAccount.validateUser(mEdtUsername.getText().toString())){
                Toast.makeText(UnitTestActivity.this, "username phai tu 6 ki tu den 22 kitu", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(UnitTestActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(){
        mEdtUsername=findViewById(R.id.edtUsername);
      //  mEdtPassword=findViewById(R.id.edtPassword);
        mBtnLogin=findViewById(R.id.btnLogin);
    }
}
