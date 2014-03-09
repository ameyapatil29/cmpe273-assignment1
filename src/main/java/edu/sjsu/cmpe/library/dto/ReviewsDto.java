package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.cmpe.library.domain.Review;

public class ReviewsDto extends LinksDto{
	// private List<ReviewDto> reviews = new ArrayList<ReviewDto>();
	 private List<Review> review;

	    public ReviewsDto(List<Review> review) {
		super();
		this.review= review;
	}

		//public void addLink(ReviewDto review) {
		//review.add(review);
	    //}

	    /**
	     * @return the links
	     */
	    public List<Review> getReviews() {
		return review;
	    }

	    /**
	     * @param links
	     *            the links to set
	     */
	    public void setReviews(List<Review> reviews) {
		this.review = reviews;
	    }

	}


