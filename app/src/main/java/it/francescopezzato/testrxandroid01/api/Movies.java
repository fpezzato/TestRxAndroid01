package it.francescopezzato.testrxandroid01.api;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by francesco on 21/10/2014.
 */
public interface Movies {

	@GET("/discover/movie?primary_release_year=2010&sort_by=vote_average.desc")
	Observable<MoviesResponse> best2010(@Query("page") Integer page);

}
