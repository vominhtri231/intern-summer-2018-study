package asiantech.internship.summer.drawer_layout.drawer_recyler_view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import asiantech.internship.summer.R;
import asiantech.internship.summer.drawer_layout.model.DrawerHeader;
import asiantech.internship.summer.drawer_layout.model.DrawerItem;

public class DrawerAdapter extends RecyclerView.Adapter {

    private List<Object> mDataSet;
    private Activity mActivity;
    private static final int VIEW_TYPE_DRAWER_HEADER = 0;
    private static final int VIEW_TYPE_DRAWER_ITEM = 1;

    public DrawerAdapter(List<Object> dataSet, Activity activity) {
        this.mDataSet = dataSet;
        mActivity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_TYPE_DRAWER_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_drawer_header, parent, false);
                return new DrawerHeaderViewHolder(view, mActivity);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_drawer_item, parent, false);
                return new DrawerItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        Object object = mDataSet.get(position);
        switch (type) {
            case VIEW_TYPE_DRAWER_HEADER:
                onBindDrawerHeaderViewHolder((DrawerHeaderViewHolder) holder, (DrawerHeader) object);
                break;
            default:
                onBindDrawerItemViewHolder((DrawerItemViewHolder) holder, (DrawerItem) object);
        }
    }

    private void onBindDrawerItemViewHolder(@NonNull DrawerItemViewHolder holder, DrawerItem item) {
        holder.getImgFeatureIcon().setImageResource(item.getImageId());
        holder.getTvFeatureName().setText(item.getTitleId());
    }

    private void onBindDrawerHeaderViewHolder(@NonNull DrawerHeaderViewHolder holder, DrawerHeader item) {
        holder.getCircleImgAvatar().setImageResource(item.getAvatarId());
        if(item.getUri()==null) {
            String[] emailList = mActivity.getResources().getStringArray(item.getEmailArrayId());
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mActivity, R.layout.support_simple_spinner_dropdown_item, emailList);
            holder.getSpnEmailChoice().setAdapter(arrayAdapter);
        }else{
            holder.getCircleImgAvatar().setImageURI(item.getUri());
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object object = mDataSet.get(position);
        if (object instanceof DrawerItem) {
            return VIEW_TYPE_DRAWER_ITEM;
        }
        return VIEW_TYPE_DRAWER_HEADER;
    }
}
