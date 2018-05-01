package education.satscoreapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import education.satscoreapplication.databinding.SchoolListRowLayoutBinding;
import education.satscoreapplication.model.SchoolObject;

import education.satscoreapplication.viewmodel.RowClickEventHandler;
import education.satscoreapplication.viewmodel.SchoolListAdapterViewModel;

/**
 * Created by ghoshr on 3/30/2018.
 */

public class SchoolListAdapter extends RecyclerView.Adapter<SchoolListAdapter.SchoolListViewHolder> implements RowClickEventHandler {

    private List<SchoolListAdapterViewModel> mSchoolList;
    private LayoutInflater mLayoutInflator;
    private static final String TAG = SchoolListAdapter.class.getSimpleName();
    private UpdateDataInterface mListenerInterface;

    public void updateList(List<SchoolObject> schoolObjectList) {

        mSchoolList = new ArrayList<SchoolListAdapterViewModel>();
        if (schoolObjectList != null && schoolObjectList.size() > 0) {
            for (SchoolObject schoolElement : schoolObjectList) {
                SchoolListAdapterViewModel schoolAdapterModel = new SchoolListAdapterViewModel();
                schoolAdapterModel.setSchoolObject(schoolElement);
                mSchoolList.add(schoolAdapterModel);
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public SchoolListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflator == null) {
            mLayoutInflator = LayoutInflater.from(parent.getContext());
        }
        SchoolListRowLayoutBinding schoolListRowBinding = SchoolListRowLayoutBinding.inflate(mLayoutInflator, parent, false);
        schoolListRowBinding.setRowClickEventHandler(this);
        return new SchoolListViewHolder(schoolListRowBinding);
    }

    @Override
    public void onBindViewHolder(SchoolListViewHolder holder, int position) {
        if (mSchoolList != null && mSchoolList.size() >= position && mSchoolList.get(position) != null) {
            holder.bind(mSchoolList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mSchoolList != null)
            return mSchoolList.size();
        else
            return 0;
    }

    public SchoolListAdapter(List<SchoolListAdapterViewModel> schoolList) {
        mSchoolList = schoolList;
    }


    public class SchoolListViewHolder extends RecyclerView.ViewHolder {

        private SchoolListRowLayoutBinding mSchoolListRowBinding;

        public SchoolListViewHolder(SchoolListRowLayoutBinding schoolListRowBinding) {
            super(schoolListRowBinding.getRoot());
            mSchoolListRowBinding = schoolListRowBinding;
        }

        public void bind(SchoolListAdapterViewModel schoolListAdapterModel) {
            mSchoolListRowBinding.setAdapterviewmodel(schoolListAdapterModel);
        }
    }

    public interface UpdateDataInterface {

        void updateUnderLyingData(List<SchoolObject> schoolObjectList);
        void replaceFragment(String schoolName);
    }


    @Override
    public void onRowClicked(View view, TextView schoolName) {
        Log.d(TAG, "school Name:" + schoolName.getText().toString());
        mListenerInterface.replaceFragment(schoolName.getText().toString());
    }


    public void setCallBackListener(UpdateDataInterface dataInterface){
        mListenerInterface=dataInterface;
    }
}
