package asiantech.internship.summer.exercise_fragment_activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import asiantech.internship.summer.R;

public class FragmentLogin extends Fragment {

    public static final String KEY_MAIL = "KEY_MAIL";
    public static final String KEY_PASS = "KEY_PASS";

    private TextView tvSignUp, tvLogin;
    private EditText edtMailLogin, edtPassWordLogin;
    private String emailReceive, passReceive;
    FragmentSignUp mFragmentSignUp = new FragmentSignUp();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Log.d("TAG", "onCreateView: ");
        initView(view);
        addListener();
        return view;
    }

    private void addListener() {
        tvSignUp.setOnClickListener(view -> {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, mFragmentSignUp);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        tvLogin.setOnClickListener(view -> {
            String email = edtMailLogin.getText().toString();
            String password = edtPassWordLogin.getText().toString();

            if(email.equals("") || password.equals("")){
                Toast.makeText(getActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            }

            else if(email.contains("@") && (password.length() >= 6)){
                if (email.equals("thanhhoang@gmail.com") && password.equals("123456") ||
                        email.equals(emailReceive) && password.equals(passReceive)) {
                    Intent intent = new Intent(getActivity(), ResultActivity.class);
                    intent.putExtra(KEY_MAIL, email);
                    intent.putExtra(KEY_PASS, password);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "Not found data", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getActivity(), "Format error!!", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void initView(View view) {
        tvSignUp = view.findViewById(R.id.tv_sign_up_first);
        tvLogin = view.findViewById(R.id.tv_login);
        edtMailLogin = view.findViewById(R.id.edt_email);
        edtPassWordLogin = view.findViewById(R.id.edt_password);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TAG", "onStart: ");
        Bundle args = getArguments();
        if (args != null) {
            emailReceive = args.getString(FragmentSignUp.MAIL_PASS);
            passReceive = args.getString(FragmentSignUp.PASSWORD_PASS);

            edtMailLogin.setText(emailReceive);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("TAG", "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TAG", "onActivityCreated: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG", "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TAG", "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TAG", "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TAG", "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("TAG", "onDetach: ");
    }
}

