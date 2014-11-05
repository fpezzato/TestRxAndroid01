package it.francescopezzato.testrxandroid01;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;

import it.francescopezzato.testrxandroid01.api.Endpoint;
import it.francescopezzato.testrxandroid01.api.Movies;
import it.francescopezzato.testrxandroid01.api.MoviesResponse;
import it.francescopezzato.testrxandroid01.api.Pagination;
import it.francescopezzato.testrxandroid01.api.Result;
import retrofit.RestAdapter;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by francesco on 19/10/2014.
 */
public class ReactiveFragment extends ListFragment {

	ArrayAdapter<String> mAdapter;
	Subscription subscription;

	//private Pagination mCurrentPagination = new Pagination(0, 0, 0);
	private PublishSubject<Pagination> mPaginationObservable = PublishSubject.create();
	private Integer mLatestPage = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mAdapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1);
		setListAdapter(mAdapter);


		setHasOptionsMenu(true);
		return super.onCreateView(inflater, container, savedInstanceState);

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getListView().setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem + visibleItemCount == totalItemCount) {
					fetchData();
				}
			}
		});

		mPaginationObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Pagination>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onNext(Pagination pagination) {

			}
		});


	}

	@Override
	public void onResume() {
		super.onResume();


	}

	private void reset() {
		mAdapter.clear();
		mPaginationObservable.onNext(new Pagination(0, 0, 0));
	}

	private void fetchData() {
		setListShown(false);

		Log.d("->","fetchData");

		Endpoint endpoint = new Endpoint();
		RestAdapter restAdapter = endpoint.getmRestAdapter();
		//final int nextPage = mLatestPage;

		Observable<MoviesResponse> movieObservable = restAdapter.create(Movies.class).best2010(mLatestPage);
		subscription = movieObservable.
			subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.map(new Func1<MoviesResponse, MoviesResponse>() {
				@Override
				public MoviesResponse call(MoviesResponse moviesResponse) {

					mPaginationObservable.onNext(new Pagination(moviesResponse.getPage(),
						moviesResponse.getTotalPages(),
						moviesResponse.getTotalResults()));

					return moviesResponse;
				}
			})
			.flatMap(new Func1<MoviesResponse, Observable<Result>>() {
				@Override
				public Observable<Result> call(MoviesResponse moviesResponse) {
					return Observable.from(moviesResponse.getResults());
				}
			})


			.subscribe(new Observer<Result>() {
				@Override
				public void onCompleted() {
					setListShown(true);
				}

				@Override
				public void onError(Throwable e) {

				}

				@Override
				public void onNext(Result result) {
					mLatestPage=+1;
					mAdapter.add(result.getTitle());
				}
			});
	}

	@Override
	public void onPause() {
		if (subscription != null && !subscription.isUnsubscribed()) {
			subscription.unsubscribe();
		}
		super.onPause();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.refresher, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_refresh) {

			reset();
			return true;
		} else return super.onOptionsItemSelected(item);

	}

	//		http://mtaulty.com/CommunityServer/blogs/mike_taultys_blog/archive/2014/02/05/rx-and-getting-paged-data-from-the-moviedb-api.aspx
	//https://groups.google.com/forum/#!topic/rxjava/iVIGMhXvjSE
	//https://bitbucket.org/krpiotrek/rxjavaexample/src/878a6f0143d5189e1e834e20c55c2c51929107d5/app/src/main/java/com/example/rxjavaexample/app/dagger/ForApplication.java?at=master
}
//http://rxmarbles.com/#map
//https://speakerdeck.com/dorvaryn/rxfy-all-the-things
