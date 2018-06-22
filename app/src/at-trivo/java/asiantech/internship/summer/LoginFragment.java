package asiantech.internship.summer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;
import java.util.regex.Pattern;

public class LoginFragment extends Fragment {
    public static final String DEFAULT_EMAIL_KEY = "DEFAULT_EMAIL_KEY";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private EditText mEdtEmail, mEdtPassword;
    private TextView mWarningText;
    OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        setUpBehavior(view);
        checkArgument();
        setTitle(R.string.log_in);
        return view;
    }

    private void setUpBehavior(View view) {
        TextView tvSignUp = view.findViewById(R.id.tvSignUp);
        TextView tvLogin = view.findViewById(R.id.tvLogin);
        mEdtEmail = view.findViewById(R.id.edtEmail);
        mEdtPassword = view.findViewById(R.id.edtPassword);
        mWarningText = view.findViewById(R.id.tvWarning);
        tvLogin.setOnClickListener(view1 -> {
            String email = mEdtEmail.getText().toString();
            String password = mEdtPassword.getText().toString();
            if (!mListener.login(email, password)) {
                setWaringMessage(R.string.login_warning);
            }
        });
        tvSignUp.setOnClickListener(view12 -> mListener.signUp());
    }

    private void checkArgument() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String defaultEmail = bundle.getString(DEFAULT_EMAIL_KEY);
            this.mEdtEmail.setText(defaultEmail);
        }
    }

    private void setTitle(int idText) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            Objects.requireNonNull(activity.getSupportActionBar()).setTitle(idText);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        boolean login(String email, String password);

        void signUp();
    }

    private void setWaringMessage(int id) {
        mWarningText.setText(id);
    }
}
