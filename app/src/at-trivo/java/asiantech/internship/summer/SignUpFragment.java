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


public class SignUpFragment extends Fragment {
    OnFragmentInteractionListener mListener;
    EditText mEdtEmail, mEdtPassword, mEditRepeatPassword;
    TextView mWarningText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        setUpBehavior(view);
        setTitle(R.string.sign_up);
        return view;
    }

    private void setUpBehavior(View view) {
        mEdtEmail = view.findViewById(R.id.edtEmail);
        mEdtPassword = view.findViewById(R.id.edtPassword);
        mEditRepeatPassword = view.findViewById(R.id.edtRepeatPassword);
        mWarningText = view.findViewById(R.id.tvWarning);
        TextView tvSignUp = view.findViewById(R.id.tvSignUp);
        tvSignUp.setOnClickListener(view1 -> {

            String email = mEdtEmail.getText().toString();
            if (!LoginFragment.VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()) {
                setWaringMessage(R.string.email_warning);
                return;
            }
            String password = mEdtPassword.getText().toString();
            if (password.length() <= 6) {
                setWaringMessage(R.string.password_warning);
                return;
            }
            String repeatPassword = mEditRepeatPassword.getText().toString();
            if (!repeatPassword.equals(password)) {
                setWaringMessage(R.string.password_repeat_warning);
                return;
            }
            mListener.addAccount(email, password);
        });
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
        void addAccount(String email, String password);
    }

    private void setWaringMessage(int id) {
        mWarningText.setText(id);
    }

}
