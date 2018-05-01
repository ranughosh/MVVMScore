package education.satscoreapplication.networkcall;


import java.util.List;

import education.satscoreapplication.model.SchoolObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by ghoshr on 3/31/2018.
 */

public interface SchoolService {
    static String  SCHOOL_LIST_EXTENSION="97mf-9njv.json";
    @GET(SCHOOL_LIST_EXTENSION)
    Observable<List<SchoolObject>> getSchoolListUrl();
}
