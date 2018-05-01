package education.satscoreapplication.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import education.satscoreapplication.R;
import education.satscoreapplication.adapter.SchoolListAdapter;
import education.satscoreapplication.databinding.SatDisplayFragmentLayoutBinding;
import education.satscoreapplication.databinding.SchoolListFragmentLayoutBinding;
import education.satscoreapplication.model.SchoolObject;
import education.satscoreapplication.viewmodel.SchoolListAdapterViewModel;
import education.satscoreapplication.viewmodel.SchoolNameViewModel;

/**
 * Created by ghoshr on 3/31/2018.
 */

public class SchoolListFragment extends Fragment {

    private View mRootView;
    private RecyclerView mSchoolRecyclerViewList;
    private SchoolListAdapter mSchoolListAdapter;
    private List<SchoolListAdapterViewModel> mSchoolAdapterDataList;
    private SchoolNameViewModel mSchoolNameModel;
    private List<SchoolObject> mSchoolDataList;
    private static final String TAG = SchoolListFragment.class.getSimpleName();
    private ReplaceFragmentInterface mListener;
    private SchoolListFragmentLayoutBinding mFragmentLayoutBinding;

    public List<SchoolObject> getSchoolDataList() {
        return mSchoolDataList;
    }

    public void setSchoolDataList(List<SchoolObject> mSchoolDataList) {
        this.mSchoolDataList = mSchoolDataList;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mFragmentLayoutBinding = DataBindingUtil.inflate(inflater,R.layout.school_list_fragment_layout,container,false);
        mRootView= mFragmentLayoutBinding.getRoot();
        mSchoolRecyclerViewList = (RecyclerView) mRootView.findViewById(R.id.school_list);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        mSchoolRecyclerViewList.setLayoutManager(layoutManager);
        mSchoolRecyclerViewList.setHasFixedSize(true);

        mSchoolAdapterDataList = new ArrayList<SchoolListAdapterViewModel>();
        mSchoolListAdapter = new SchoolListAdapter(mSchoolAdapterDataList);

        if (mSchoolNameModel == null) {
            mSchoolNameModel = new SchoolNameViewModel();
        }

        mSchoolNameModel.setSchoolListFragment(this);
        mSchoolNameModel.setSchoolListAdapter(mSchoolListAdapter);
        mSchoolRecyclerViewList.setAdapter(mSchoolListAdapter);
        mFragmentLayoutBinding.setSnvm(mSchoolNameModel);

        if (getSchoolDataList() != null && getSchoolDataList().size() > 0) {
            mSchoolListAdapter.updateList(getSchoolDataList());
        }
        else{//for  handling configuration change
            mSchoolNameModel.loadSchoolData();
        }

        return mRootView;
    }

    public void replaceFragmentFromParent(String schoolName) {
        Log.d(TAG,"schoolName:"+schoolName);
        if(mListener!=null) {
            mListener.replaceWithSatScore(schoolName);
        }
    }

    public interface ReplaceFragmentInterface {
        void replaceWithSatScore(String schoolName);
    }

    public void setCallBackListener(ReplaceFragmentInterface listener){
        mListener=listener;
    }

    @Override
    public void onStop () {
        super.onStop();
        if (mSchoolNameModel != null) {
            mSchoolNameModel.cancelRequest();
        }
    }
}
