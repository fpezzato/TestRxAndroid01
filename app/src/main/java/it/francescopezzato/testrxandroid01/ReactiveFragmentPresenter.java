package it.francescopezzato.testrxandroid01;

/**
 * Created by francesco on 02/11/2014.
 */
public class ReactiveFragmentPresenter {

	private ReactiveFragment mFragment;

	public ReactiveFragmentPresenter withFragment(ReactiveFragment fragmnet) {

		mFragment = fragmnet;
		return this;
	}


}
