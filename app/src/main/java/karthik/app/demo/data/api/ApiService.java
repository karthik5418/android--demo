package karthik.app.demo.data.api;

import org.jsoup.nodes.Document;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by NiCK on 1/3/2018.
 */

public interface ApiService {

    @GET("search?")
    Observable<Document> getSearchList(@Query("keyword") String keyword, @Query("sort") String sort);

}
