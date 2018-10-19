package asiantech.internship.summer.file_storage.sqlite.view.popup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import asiantech.internship.summer.R;

public class AddCompanyDialogFragment extends DialogFragment {

    public static final String ADD_COMPANY_DIALOG_TAG = "add company tag";
    private AddCompanyDialogListener mListener;
    private EditText mEdtCompanyAddress;
    private EditText mEdtCompanyName;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(), R.layout.dialog_add_company, null);
        mEdtCompanyAddress = view.findViewById(R.id.edtCompanyAddress);
        mEdtCompanyName = view.findViewById(R.id.edtCompanyName);

        builder.setView(view)
                .setPositiveButton(R.string.ok, (dialogInterface, i) ->
                        mListener.addCompany(mEdtCompanyName.getText().toString(), mEdtCompanyAddress.getText().toString()))
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> dialogInterface.cancel());
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof AddCompanyDialogListener) {
            mListener = (AddCompanyDialogListener) activity;
        }
    }

    public interface AddCompanyDialogListener {
        void addCompany(String name, String address);
    }
}
