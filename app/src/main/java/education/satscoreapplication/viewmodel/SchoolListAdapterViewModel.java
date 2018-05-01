package education.satscoreapplication.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import education.satscoreapplication.model.SchoolObject;

/**
 * Created by ghoshr on 3/31/2018.
 */

public class SchoolListAdapterViewModel extends BaseObservable {

    private SchoolObject mSchoolObject;

    public void setSchoolObject(SchoolObject schoolObject){
        mSchoolObject=schoolObject;

    }

    public SchoolObject getSchoolObject(){
        return mSchoolObject;
    }

    @Bindable
    public String getSchoolName(){
        return mSchoolObject.getSchoolName();
    }
    @Bindable
    public String getSchoolLocation(){
        return mSchoolObject.getSchoolLocation();
    }
    @Bindable
    public String getSchoolWebsite(){
          return mSchoolObject.getSchoolWebsite();
    }


}
