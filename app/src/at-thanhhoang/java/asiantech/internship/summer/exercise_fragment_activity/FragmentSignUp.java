package asiantech.internship.summer.exercise_fragment_activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import asiantech.internship.summer.R;

public class FragmentSignUp extends Fragment {

    public static final String MAIL_PASS = "sent mail";
    public static final String PASSWORD_PASS = "sent password";
    private EditText edtEmail, edtPassword, edtConfirmPassWord;
    private TextView tvSignUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        FragmentActivity fragmentActivity = (FragmentActivity) getActivity();
        Objects.requireNonNull(fragmentActivity).setTitleToolbar(FragmentActivity.TITLE_SIGNUP);

//        Log.d("tag", "onCreateView: ");

        initView(view);
        addListener();
        return view;
    }

    @SuppressLint("ResourceType")
    private void addListener() {
        tvSignUp.setOnClickListener(view -> {
            String mail = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString();
            String confirmPassword = edtConfirmPassWord.getText().toString();

            if (mail.equals("") || password.equals("") || confirmPassword.equals("")) {
                Toast.makeText(getActivity(), "Please enter all fields ", Toast.LENGTH_SHORT).show();
            } else if (CheckValid.isEmailValid(mail) && CheckValid.isPasswordValid(password) && CheckValid.isPasswordValid(confirmPassword))
            {
                if (password.equals(confirmPassword)) {
                    FragmentLogin mFragmentLogin = new FragmentLogin();

                    Bundle args = new Bundle();
                    args.putString(MAIL_PASS, mail);
                    args.putString(PASSWORD_PASS, password);
                    mFragmentLogin.setArguments(args);

                    FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_right_out, R.anim.slide_right_in,
                            R.anim.slide_left_out);
                    transaction.replace(R.id.container, mFragmentLogin);
                    transaction.addToBackStack(null);

                    transaction.commit();
                } else {
                    Toast.makeText(getActivity(), "Passwords must match!!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getActivity(), "Error format!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(View view) {
        edtEmail = view.findViewById(R.id.edt_email_sign_up);
        edtPassword = view.findViewById(R.id.edt_password_sign_up);
        edtConfirmPassWord = view.findViewById(R.id.edt_confirm_password_sign_up);

        tvSignUp = view.findViewById(R.id.tv_sign_up_second);
    }

    @Override
    public void onStart() {
        super.onStart();
//        Log.d("tag", "onStart: ");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        Log.d("tag", "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d("tag", "onCreate: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Log.d("tag", "onActivityCreated: ");
    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.d("tag", "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
//        Log.d("tag", "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
//        Log.d("tag", "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Log.d("tag", "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Log.d("tag", "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        Log.d("tag", "onDetach: ");
    }
}


