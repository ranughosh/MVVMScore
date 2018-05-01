package education.satscoreapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ghoshr on 3/31/2018.
 */

public class SchoolObject {

    @SerializedName("school_name")
    private String mSchoolName;
    @SerializedName("location")
    private String mSchoolLocation;
    @SerializedName("website")
    private String mSchoolWebsite;

    public String getSchoolName() {
        return mSchoolName;
    }

    public void setSchoolName(String mScoolName) {
        this.mSchoolName = mScoolName;
    }

    public String getSchoolLocation() {
        return mSchoolLocation;
    }

    public void setSchoolLocation(String mScoolLocation) {
        this.mSchoolLocation = mScoolLocation;
    }

    public String getSchoolWebsite() {
        return mSchoolWebsite;
    }

    public void setSchoolWebsite(String mScoolWebsite) {
        this.mSchoolWebsite = mScoolWebsite;
    }

}
