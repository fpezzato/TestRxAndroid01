package it.francescopezzato.testrxandroid01.api;

/**
 * Created by francesco on 02/11/2014.
 */
public class Pagination {


	private int mCurrPage = 0;
	private int mTotalPages = 0;
	private int mTotalResults = 0;

	public Pagination(int mCurrPage, int mTotalPages, int mTotalResults) {
		this.mCurrPage = mCurrPage;
		this.mTotalPages = mTotalPages;
		this.mTotalResults = mTotalResults;
	}

	public int getCurrPage() {
		return mCurrPage;
	}

	public int getTotalPages() {
		return mTotalPages;
	}

	public int getTotalResults() {
		return mTotalResults;
	}

}
