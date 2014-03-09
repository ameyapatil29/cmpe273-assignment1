package edu.sjsu.cmpe.library.domain;



import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"isbn","title","publication-date","language","num-pages","status","reviews","author"})

public class Book {
	@JsonProperty("isbn")
    private long isbn;
	
	@JsonProperty("title")
    private String title;
	
	@JsonProperty("publication-date")
    private String publicationdate;
	
	@JsonProperty("language")
    private String language;
	
	@JsonProperty("num-pages")
    private Integer numpages;
	
	@JsonProperty("status")
    private String status;
	
	@JsonProperty("reviews")
    //private Review reviews = new Review();
	private List<Review> reviews = new ArrayList<Review>();
	
    
	@JsonProperty("authors")
	private ArrayList<Author> author = new ArrayList<Author>();
   // private Author author;

    // add more fields here

    /**
     * @return the isbn
     */
    @JsonProperty
    @NotNull
    public long getIsbn() {	
	return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
    
    public void setStatus ( String status){
    	this.status = status;
    }
    
    public String getStatus (){
    	return status;
    }
    
    public void setPublication(String publication_date) {
    	this.publicationdate = publication_date;
    }
    
    public String getPublication() {
    	return publicationdate;
    }
    
    public void setLanguage ( String language){
    	this.language = language;
    }
    
    public String getLanguage (){
    	return language;
    }
    
    public void setNumpages ( Integer number_of_pages ){
    	this.numpages = number_of_pages ;
    }
    
    public Integer getNumpages (){
    	return numpages;
    }
    
    //public void setReview( Review review){
    	//this.reviews = review;
    //}
    
    //public Review getReview(id){
    //	return this.reviews;
    //}
    
    public List<Review> getReview() {
    	return reviews;
        }

        /**
         * @param review
         *            the title to set
         */
        public void setReview(ArrayList<Review> reviews) {
    	this.reviews = reviews;
        }
        
        public  Review getbookReview(int reviewid) {
        	return this.reviews.get(reviewid);
            }
        
        /**
         * @return the author
         */
        public ArrayList<Author> getAuthor() {
    	return author;
        }

        /**
         * @param author
         *            the title to set
         */
        public void setAuthor(ArrayList<Author> author) {
    	this.author = author;
        }
}
