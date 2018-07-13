package asiantech.internship.summer.file_storage.sqlite.view.company_fragment;

import android.content.Context;
import android.os.Bundle;

import java.util.Objects;

import asiantech.internship.summer.file_storage.sqlite.database.CompanyDao;
import asiantech.internship.summer.file_storage.sqlite.view.DatabaseActivity;
import asiantech.internship.summer.file_storage.sqlite.view.SharedFragment;
import asiantech.internship.summer.file_storage.sqlite.view.popup.AddCompanyDialogFragment;

public class CompanyFragment extends SharedFragment {

    private OnFragmentInteractionListener mListener;
    private CompanyDao mCompanyDao;

    protected void getAdditionInfo() {
    }

    protected void setUpDao() {
        mCompanyDao = new CompanyDao(getContext());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    protected void setUpRecyclerView() {
        super.setUpRecyclerView();
        mDataSet.addAll(mCompanyDao.getCompanies());
        CompanyAdapter adapter = new CompanyAdapter(mDataSet, mListener);
        mRecyclerView.setAdapter(adapter);
    }

    protected void onAddButtonClicked() {
        AddCompanyDialogFragment addCompanyDialog = new AddCompanyDialogFragment();
        addCompanyDialog.show(Objects.requireNonNull(getActivity()).getFragmentManager(), AddCompanyDialogFragment.ADD_COMPANY_DIALOG_TAG);
    }

    public void updateCompanies() {
        mDataSet.clear();
        mDataSet.addAll(mCompanyDao.getCompanies());
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    public interface OnFragmentInteractionListener {
        void openEmployeeFragment(int companyId);
    }
}
