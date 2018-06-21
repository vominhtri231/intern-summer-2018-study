package asiantech.internship.summer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class LoginFragment extends Fragment {

    private EditText mEdtEmail,mEdtPassword;
    OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        TextView tvSignUp=view.findViewById(R.id.tvSignUp);
        TextView tvLogin=view.findViewById(R.id.tvLogin);
        mEdtEmail=view.findViewById(R.id.edtEmail);
        mEdtPassword=view.findViewById(R.id.edtPassword);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.login(mEdtEmail.getText().toString(),mEdtPassword.getText().toString());
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.signUp();
            }
        });
        return view;
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
        void login(String email,String password);
        void signUp();
    }
}
