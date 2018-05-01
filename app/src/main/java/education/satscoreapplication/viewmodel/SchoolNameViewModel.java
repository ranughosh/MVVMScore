package education.satscoreapplication.viewmodel;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import java.util.List;

import education.satscoreapplication.BR;
import education.satscoreapplication.adapter.SchoolListAdapter;
import education.satscoreapplication.model.SchoolObject;
import education.satscoreapplication.networkcall.ApiUtils;
import education.satscoreapplication.networkcall.SchoolService;

import education.satscoreapplication.ui.SchoolListFragment;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ghoshr on 3/31/2018.
 */

public class SchoolNameViewModel extends BaseObservable implements SchoolListAdapter.UpdateDataInterface {

    public SchoolNameViewModel() {
        mSchoolService = ApiUtils.getSchoolService();
        updateInProgress(false);

    }

    ;

    private SchoolService mSchoolService;
    private static String TAG = SchoolNameViewModel.class.getSimpleName();
    private Observable<List<SchoolObject>> mSchoolListObservable;
    private SchoolListFragment mSchoolListFragment;
    private Disposable mSchoolListRequestDisposable;
    @Bindable
    private boolean inProgress;

    public boolean isInProgress() {
        return inProgress;
    }


    public SchoolListFragment getSchoolListFragment() {
        return mSchoolListFragment;
    }

    public void setSchoolListFragment(SchoolListFragment mSchoolListFragment) {
        this.mSchoolListFragment = mSchoolListFragment;
    }


    public void setSchoolListAdapter(SchoolListAdapter mSchoolListAdapter) {
        this.mSchoolListAdapter = mSchoolListAdapter;
        mSchoolListAdapter.setCallBackListener(this);
    }

    private SchoolListAdapter mSchoolListAdapter;

    private Observer<List<SchoolObject>> mSchoolListObserver = new Observer<List<SchoolObject>>() {

        @Override
        public void onSubscribe(@NonNull Disposable d) {
            mSchoolListRequestDisposable = d;
        }

        @Override
        public void onComplete() {
            updateInProgress(false);
            Log.d(TAG, "on completed");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "on Error");
            updateInProgress(false);
        }

        @Override
        public void onNext(List<SchoolObject> schoolObjectApiResponse) {

            if (schoolObjectApiResponse != null) {
                Log.d(TAG, "school count:" + schoolObjectApiResponse.size());
                updateUnderLyingData(schoolObjectApiResponse);
                mSchoolListFragment.setSchoolDataList(schoolObjectApiResponse);

            }

        }
    };

    private void updateInProgress(boolean inProgress) {
        this.inProgress = inProgress;
        notifyPropertyChanged(BR.inProgress);
    }

    public void loadSchoolData() {
        updateInProgress(true);
        mSchoolListObservable = mSchoolService.getSchoolListUrl();
        mSchoolListObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(mSchoolListObserver);
    }

    @Override
    public void updateUnderLyingData(List<SchoolObject> schoolObjectList) {
        mSchoolListAdapter.updateList(schoolObjectList);
    }

    @Override
    public void replaceFragment(String schoolName) {
        mSchoolListFragment.replaceFragmentFromParent(schoolName);
    }

    public void cancelRequest() {
        if (mSchoolListRequestDisposable != null && !mSchoolListRequestDisposable.isDisposed()) {
            mSchoolListRequestDisposable.dispose();
        }

    }
}
