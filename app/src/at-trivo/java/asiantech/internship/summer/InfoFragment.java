package asiantech.internship.summer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;


public class InfoFragment extends Fragment {
    public static String HELLO_KEY = "HELLO_KEY";

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        TextView tvHello = view.findViewById(R.id.tvHello);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String receiveMessage = bundle.getString(HELLO_KEY);
            tvHello.setText("Hello " + receiveMessage);
        }
        setTitle(R.string.info);
        return view;
    }

    private void setTitle(int idText) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            Objects.requireNonNull(activity.getSupportActionBar()).setTitle(idText);
        }
    }
}
