package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.Review;

public class ReviewDto extends LinksDto {
	
	private Review review = new Review();
	
	private Integer rating = 5;
	private String comment = "none";
	
	
	
	public ReviewDto(Integer rating, String comment) {
		super();
		this.rating = rating;
		this.comment = comment;
	}

	

	public ReviewDto(long rating2, String comment2) {
		this.rating = (int) rating2;
		this.comment = comment2;
	}



	public ReviewDto(Review getbookReview) {
		super();
		this.review = getbookReview;
	}



	public void setRating(Integer rating){
		this.rating = rating;
	}
	
	public Integer getRating() {
		return rating;
	}
	
	public void setComment(String comment){
		this.comment = comment;
	}
	
	public String getComment(){
		return comment;
	}

}
