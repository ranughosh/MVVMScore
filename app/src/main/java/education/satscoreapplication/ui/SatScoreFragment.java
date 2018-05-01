package education.satscoreapplication.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import education.satscoreapplication.R;
import education.satscoreapplication.appconstants.Constants;
import education.satscoreapplication.databinding.SatDisplayFragmentLayoutBinding;
import education.satscoreapplication.model.SatScore;
import education.satscoreapplication.viewmodel.SatViewModel;

/**
 * Created by ghoshr on 3/31/2018.
 */

public class SatScoreFragment extends Fragment {

    private SatDisplayFragmentLayoutBinding mSatDisplayLayoutBinding;
    private SatScore mSatScore;
    private SatViewModel mSatViewModel;
    private String mSchoolName;

    public SatScore getSatScore() {
        return mSatScore;
    }

    public void setSatScore(SatScore mSatScore) {
        this.mSatScore = mSatScore;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if(getArguments()!=null){
            mSchoolName=getArguments().getString(Constants.SCHOOL_NAME,"");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mSatDisplayLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.sat_display_fragment_layout,container,false);
        if(mSatViewModel==null){
            mSatViewModel=new SatViewModel();
        }
        mSatDisplayLayoutBinding.setSatviewmodel(mSatViewModel);
        if(getSatScore()==null){
            mSatViewModel.loadSatData(mSchoolName);//get the value from bundle
        }
        mSatViewModel.setSatScoreFragment(this);
        mSatDisplayLayoutBinding.setSatviewmodel(mSatViewModel);
        return mSatDisplayLayoutBinding.getRoot();
    }

    @Override
    public void onStop () {
        super.onStop();
        if (mSatViewModel != null) {
            mSatViewModel.cancelRequest();
        }
    }
}
