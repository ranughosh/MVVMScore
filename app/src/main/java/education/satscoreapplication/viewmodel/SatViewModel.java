package education.satscoreapplication.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.util.Log;

import java.net.URLEncoder;
import java.util.List;

import education.satscoreapplication.BR;
import education.satscoreapplication.appconstants.Constants;
import education.satscoreapplication.model.SatScore;
import education.satscoreapplication.networkcall.ApiUtils;
import education.satscoreapplication.networkcall.SatResultService;
import education.satscoreapplication.ui.SatScoreFragment;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ghoshr on 3/31/2018.
 */

public class SatViewModel extends BaseObservable{

    public SatViewModel(){
        mSatResultService= ApiUtils.getSatResultService();
        setErrorFlag(false);
    }

    private static String TAG = SatViewModel.class.getSimpleName();
    private SatResultService mSatResultService;
    @Bindable
    private String mSchoolName;
    @Bindable
    private String mNoOfTestTakers;
    @Bindable
    private String mAvgMathScore;
    @Bindable
    private String mAvgReadingScore;
    @Bindable
    private String mAvgWritingScore;
    @Bindable
    private boolean mErrorFlag;
    private Disposable mSatScoreRequestDisposable;

    private Observable<List<SatScore>> mSatScoreObservable;

    private Observer<List<SatScore>> mSatScoreObserver = new Observer<List<SatScore>>() {
        @Override
        public void onSubscribe(Disposable d) {
            mSatScoreRequestDisposable=d;
        }

        @Override
        public void onNext(List<SatScore> satScoreList) {
            Log.d(TAG,"onNext:"+satScoreList.size());
           if(satScoreList!=null && satScoreList.size()>0){
               setErrorFlag(false);
               SatScore satScoreElement = satScoreList.get(0);//expecting only one element per school
               if(satScoreElement!=null) {
                   setAvgMathScore(satScoreElement.getAvgMathScore());
                   setAvgReadingScore(satScoreElement.getAvgReadingScore());
                   setAvgWritingScore(satScoreElement.getAvgWritingScore());
                   setNoOfTestTakers(satScoreElement.getNumOfTestTakers());
                   setSchoolName(satScoreElement.getSchoolName());
                   mSatScoreFragment.setSatScore(satScoreElement);
               }
           }
           else{
               setErrorFlag(true);
           }
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG,"onError:"+e.toString());
            setErrorFlag(true);
            setAvgMathScore(null);
            setAvgReadingScore(null);
            setAvgWritingScore(null);
            setNoOfTestTakers(null);
            setSchoolName(null);
        }

        @Override
        public void onComplete() {
            Log.d(TAG,"onComplete");
        }
    };

    private SatScoreFragment mSatScoreFragment;

    public SatScoreFragment getSatScoreFragment() {
        return mSatScoreFragment;
    }

    public void setSatScoreFragment(SatScoreFragment mSatScoreFragment) {
        this.mSatScoreFragment = mSatScoreFragment;
    }


    public boolean isErrorFlag() {
        return mErrorFlag;
    }

    public void setErrorFlag(boolean isError) {
        this.mErrorFlag =isError;
       notifyPropertyChanged(BR.errorFlag);
    }

    public String getSchoolName() {
        return mSchoolName;
    }

    public void setSchoolName(String mSchoolName) {
        this.mSchoolName = mSchoolName;
        notifyPropertyChanged(BR.schoolName);
    }

    public String getNoOfTestTakers() {
        return mNoOfTestTakers;
    }

    public void setNoOfTestTakers(String mNoOfTestTakers) {
        this.mNoOfTestTakers = mNoOfTestTakers;
        notifyPropertyChanged(BR.noOfTestTakers);
    }

    public String getAvgMathScore() {
        return mAvgMathScore;
    }

    public void setAvgMathScore(String mAvgMathScore) {
        this.mAvgMathScore = mAvgMathScore;
        notifyPropertyChanged(BR.avgMathScore);
    }

    public String getAvgReadingScore() {
        return mAvgReadingScore;
    }

    public void setAvgReadingScore(String mAvgReadingScore) {
        this.mAvgReadingScore = mAvgReadingScore;
        notifyPropertyChanged(BR.avgReadingScore);
    }

    public String getAvgWritingScore() {
        return mAvgWritingScore;
    }

    public void setAvgWritingScore(String mAvgWritingScore) {
        this.mAvgWritingScore = mAvgWritingScore;
        notifyPropertyChanged(BR.avgWritingScore);
    }

   /* public String getUrl(String schoolName) {
        StringBuilder strUrl = new StringBuilder(Constants.schoolEndPointUrl);
        strUrl.append("734v-jeq5.json?school_name="+schoolName);

        Log.d(TAG, "str url:" + strUrl.toString());
        return strUrl.toString();

    }*/

    public void loadSatData(String schoolName){
        Log.d(TAG,"school name:"+schoolName);
        if(!TextUtils.isEmpty(schoolName)) {
            try {
                schoolName = URLEncoder.encode(schoolName, Constants.UTF_8);
                mSatScoreObservable = mSatResultService.getSatUrl(schoolName);
                mSatScoreObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mSatScoreObserver);
            } catch (Exception ex) {
                Log.e(TAG, "Failed to encode schoolName: " + schoolName);
            }
        }
    }

    public void cancelRequest() {
        if (mSatScoreRequestDisposable != null && !mSatScoreRequestDisposable.isDisposed()) {
            mSatScoreRequestDisposable.dispose();
        }

    }
}
