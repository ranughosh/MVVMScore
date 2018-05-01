package education.satscoreapplication.networkcall;

import java.util.List;

import education.satscoreapplication.model.SatScore;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by ghoshr on 3/31/2018.
 */

public interface SatResultService {

    static String  SAT_SCORE_EXTENSION="734v-jeq5.json";
    static String  QUERY_PARAMETER="school_name";
    @GET(SAT_SCORE_EXTENSION)
    Observable<List<SatScore>> getSatUrl(@Query(QUERY_PARAMETER) String schoolName);//encoded school name
    /*@GET
    Observable<SatScore> getSatUrl(@Url String url);*/
}
