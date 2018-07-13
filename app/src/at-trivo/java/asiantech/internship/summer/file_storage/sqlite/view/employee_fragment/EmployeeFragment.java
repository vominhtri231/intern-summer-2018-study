package asiantech.internship.summer.file_storage.sqlite.view.employee_fragment;

import android.os.Bundle;

import java.util.Objects;

import asiantech.internship.summer.R;
import asiantech.internship.summer.file_storage.sqlite.database.CompanyDao;
import asiantech.internship.summer.file_storage.sqlite.database.EmployeeDao;
import asiantech.internship.summer.file_storage.sqlite.view.DatabaseActivity;
import asiantech.internship.summer.file_storage.sqlite.view.SharedFragment;
import asiantech.internship.summer.file_storage.sqlite.view.popup.AddEmployeeDialogFragment;

public class EmployeeFragment extends SharedFragment {

    private int mCompanyId;
    private String mTitle;
    private EmployeeDao mEmployeeDao;

    protected void getAdditionInfo() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCompanyId = bundle.getInt(DatabaseActivity.COMPANY_ID_KEY);
            mTitle = bundle.getString(DatabaseActivity.COMPANY_NAME_KEY);
        }
    }

    protected void setUpDao(){
        mEmployeeDao =new EmployeeDao(getContext());
    }

    protected void setUpRecyclerView() {
        super.setUpRecyclerView();
        mDataSet.add(mTitle);
        mDataSet.addAll(mEmployeeDao.getEmployees(mCompanyId));
        EmployeeAdapter mAdapter = new EmployeeAdapter(mDataSet, getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void onAddButtonClicked() {
        AddEmployeeDialogFragment addEmployeeDialogFragment = new AddEmployeeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(DatabaseActivity.COMPANY_ID_KEY, mCompanyId);
        addEmployeeDialogFragment.setArguments(bundle);
        addEmployeeDialogFragment.show(Objects.requireNonNull(getActivity()).getFragmentManager(), AddEmployeeDialogFragment.ADD_EMPLOYEE_DIALOG_TAG);
    }

    public void updateEmployees() {
        mDataSet.clear();
        mDataSet.add(mTitle);
        mDataSet.addAll(mEmployeeDao.getEmployees(mCompanyId));
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}