package it.francescopezzato.testrxandroid01.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Result {

	@Expose
	private Boolean adult;
	@SerializedName("backdrop_path")
	@Expose
	private String backdropPath;
	@Expose
	private Integer id;
	@SerializedName("original_title")
	@Expose
	private String originalTitle;
	@SerializedName("release_date")
	@Expose
	private String releaseDate;
	@SerializedName("poster_path")
	@Expose
	private String posterPath;
	@Expose
	private Double popularity;
	@Expose
	private String title;
	@SerializedName("vote_average")
	@Expose
	private Double voteAverage;
	@SerializedName("vote_count")
	@Expose
	private Integer voteCount;

	public Boolean getAdult() {
		return adult;
	}

	public void setAdult(Boolean adult) {
		this.adult = adult;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public Double getPopularity() {
		return popularity;
	}

	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(Double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

}
