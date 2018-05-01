package education.satscoreapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ghoshr on 3/31/2018.
 */

public class SatScore {
    @SerializedName("num_of_sat_test_takers")
    private String mNumOfTestTakers;
    @SerializedName("sat_critical_reading_avg_score")
    private String mAvgReadingScore;
    @SerializedName("sat_math_avg_score")
    private String mAvgMathScore;
    @SerializedName("sat_writing_avg_score")
    private String mAvgWritingScore;
    @SerializedName("school_name")
    private String mSchoolName;

    public String getNumOfTestTakers() {
        return mNumOfTestTakers;
    }

    public void setNumOfTestTakers(String mNumOfTestTakers) {
        this.mNumOfTestTakers = mNumOfTestTakers;
    }

    public String getAvgReadingScore() {
        return mAvgReadingScore;
    }

    public void setAvgReadingScore(String mAvgReadingScore) {
        this.mAvgReadingScore = mAvgReadingScore;
    }

    public String getAvgMathScore() {
        return mAvgMathScore;
    }

    public void setAvgMathScore(String mAvgMathScore) {
        this.mAvgMathScore = mAvgMathScore;
    }

    public String getAvgWritingScore() {
        return mAvgWritingScore;
    }

    public void setAvgWritingScore(String mAvgWritingScore) {
        this.mAvgWritingScore = mAvgWritingScore;
    }

    public String getSchoolName() {
        return mSchoolName;
    }

    public void setSchoolName(String mSchoolName) {
        this.mSchoolName = mSchoolName;
    }
}
