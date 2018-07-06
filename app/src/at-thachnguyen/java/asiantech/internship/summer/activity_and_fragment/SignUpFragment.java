package asiantech.internship.summer.activity_and_fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Objects;
import asiantech.internship.summer.R;

public class SignUpFragment extends Fragment {
    private static final String DATA_RECEIVE_EMAIL = "mail";
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private TextView tvSignUp;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        init(contentView);
        toolbar.setTitle("Sign up");
        tvSignUp.setOnClickListener(view -> {
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            String confirmPassword = edtConfirmPassword.getText().toString();
            signUp(contentView, email, password, confirmPassword);
        });
        return contentView;
    }

    private void init(View contentView) {
        edtEmail = contentView.findViewById(R.id.edtEmail);
        edtPassword = contentView.findViewById(R.id.edtPassword);
        edtConfirmPassword = contentView.findViewById(R.id.edtConfirmPassword);
        tvSignUp = contentView.findViewById(R.id.tvSignUp);
        toolbar = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar);
    }

    private void signUp(View contentView, String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword) || password.equals("") || confirmPassword.equals("") || CheckAccount.checkPassword(password)) {
            Toast.makeText(contentView.getContext(), "Sorry!!! Password or confirm password is incorrect!", Toast.LENGTH_SHORT).show();
        } else {
            if (CheckAccount.validateMail(email) || email.equals("")) {
                Toast.makeText(contentView.getContext(), "Sorry!!! Your email is incorrect!", Toast.LENGTH_SHORT).show();
            } else {
                LoginFragment mLoginFragment = new LoginFragment();
                Bundle mBundle = new Bundle();
                mBundle.putString(DATA_RECEIVE_EMAIL, email);
                mLoginFragment.setArguments(mBundle);
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_left_in, R.anim.slide_right_out,
                        R.anim.slide_right_in, R.anim.slide_left_out)
                        .replace(R.id.fragmentContainer, mLoginFragment)
                        .commit();
            }
        }

    }
}
