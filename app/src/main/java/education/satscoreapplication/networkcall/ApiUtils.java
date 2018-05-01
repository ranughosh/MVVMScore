package education.satscoreapplication.networkcall;

import education.satscoreapplication.appconstants.Constants;

/**
 * Created by ghoshr on 3/31/2018.
 */

public class ApiUtils {
    public static SchoolService getSchoolService() {
        return RetrofitClient.getClient(Constants.schoolEndPointUrl).create(SchoolService.class);
    }

    public static SatResultService getSatResultService() {
        return RetrofitClient.getClient(Constants.schoolEndPointUrl).create(SatResultService.class);
    }
}
