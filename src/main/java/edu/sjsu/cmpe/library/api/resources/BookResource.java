package edu.sjsu.cmpe.library.api.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    /** bookRepository instance */
    private final BookRepositoryInterface bookRepository;

    /**
     * BookResource constructor
     * 
     * @param bookRepository
     *            a BookRepository instance
     */
    public BookResource(BookRepositoryInterface bookRepository) {
	this.bookRepository = bookRepository;
    }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") LongParam isbn) {
	Book book = bookRepository.getBookByISBN(isbn.get());
	BookDto bookResponse = new BookDto(book);
	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),
		"GET"));
	bookResponse.addLink(new LinkDto("update-book",
		"/books/" + book.getIsbn(), "PUT"));
	bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(),
		"DELETE"));
	bookResponse.addLink(new LinkDto("create-review","/books/" + book.getIsbn()+"/reviews",
			"POST"));

	return bookResponse;
	
	//Map<String, Object> responseMap = new HashMap<String, Object>();
	//List<LinkDto> links = new ArrayList<LinkDto>();
	//links.add(new LinkDto("view-book", "/books/" + book.getIsbn(), "GET"));
	//links.add(new LinkDto("update-book","/books/" + book.getIsbn(),"PUT"));
	//links.add(new LinkDto("delete-book","/books/" + book.getIsbn(),"DELETE"));
	//links.add(new LinkDto("create-review","/books/" + book.getIsbn() + "/reviews","POST"));

	//responseMap.put("links", links);

	//return Response.status(200).entity(responseMap).build();
    }
    
    @DELETE
    @Timed(name = "delete-book")
    @Path("/{isbn}")
    public Response deleteBook(@PathParam("isbn") LongParam isbn) {
    	
    	Book savedBook = bookRepository.getBookByISBN(isbn.get());
    	bookRepository.deleteBookByISBN(isbn.get());
       
    	//BookDto bookResponse = new BookDto(savedBook);
    	
    	//bookResponse.addLink(new LinkDto("create-book", "/books", "POST"));
    	
    	//return Response.status(200).entity(bookResponse).build();
    	
    	Map<String, Object> responseMap = new HashMap<String, Object>();
    	List<LinkDto> links = new ArrayList<LinkDto>();
    	//links.add(new LinkDto("view-book", "/books/" + savedBook.getIsbn(), "GET"));
    	links.add(new LinkDto("create-book","/books/" + savedBook.getIsbn(),"POST"));
    	
    	responseMap.put("links", links);

    	return Response.status(201).entity(responseMap).build();
    }
    

    @POST
    @Timed(name = "create-book")
    public Response createBook(Book request) {
	// Store the new book in the BookRepository so that we can retrieve it.
	Book savedBook = bookRepository.saveBook(request);

	//String location = "/books/" + savedBook.getIsbn();
	//BookDto bookResponse = new BookDto(savedBook);
	//bookResponse.addLink(new LinkDto("view-book", location, "GET"));
	//bookResponse.addLink(new LinkDto("update-book", location, "POST"));
	// Add other links if needed

	//return Response.status(201).entity(bookResponse).build();
	
	Map<String, Object> responseMap = new HashMap<String, Object>();
	List<LinkDto> links = new ArrayList<LinkDto>();
	links.add(new LinkDto("view-book", "/books/" + savedBook.getIsbn(), "GET"));
	links.add(new LinkDto("update-book","/books/" + savedBook.getIsbn(),"PUT"));
	//links.add(new LinkDto("delete-book","/books/" + book.getIsbn(),"DELETE"));
	//links.add(new LinkDto("create-review","/books/" + book.getIsbn() + "/reviews","POST"));

	responseMap.put("links", links);

	return Response.status(200).entity(responseMap).build();
    }
    
    
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public Response updateBook(@PathParam("isbn") LongParam isbn, @QueryParam("status") String status) {

		Book retrieveBook= bookRepository.getBookByISBN(isbn.get());
		retrieveBook.setStatus(status);

		//BookDto bookResponse = new BookDto(retrieveBook);
		//bookResponse.addLink(new LinkDto("view-book", "/books/" + retrieveBook.getIsbn(), "GET"));
		//bookResponse.addLink(new LinkDto("update-book","/books/" + retrieveBook.getIsbn(), "PUT"));
		//bookResponse.addLink(new LinkDto("delete-book","/books/" + retrieveBook.getIsbn(), "DELETE"));
		//bookResponse.addLink(new LinkDto("create-review","/books/" + retrieveBook.getIsbn() + "/reviews", "POST"));
		/*if (retrieveBook.getReviews().size() !=0 ){
			bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + retrieveBook.getIsbn() + "/reviews", "GET"));
			}*/

	//return Response.ok().entity(bookResponse.getLinks()).build();
	
	Map<String, Object> responseMap = new HashMap<String, Object>();
	List<LinkDto> links = new ArrayList<LinkDto>();
	links.add(new LinkDto("view-book", "/books/" + retrieveBook.getIsbn(), "GET"));
	links.add(new LinkDto("update-book","/books/" + retrieveBook.getIsbn(),"PUT"));
	links.add(new LinkDto("delete-book","/books/" + retrieveBook.getIsbn(),"DELETE"));
	links.add(new LinkDto("create-review","/books/" + retrieveBook.getIsbn() + "/reviews","POST"));

	responseMap.put("links", links);

	return Response.status(200).entity(responseMap).build();
    }

    
    @POST
    @Path("/{isbn}/reviews")
    @Timed (name = "create-review")
    public Response createReview(@PathParam("isbn") LongParam isbn, Review review){
    	
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	
    	bookRepository.saveReview(review);
    	
    	ReviewDto reviewdto = new ReviewDto(review.getRating(), review.getComment());
    	
    	reviewdto.addLink(new LinkDto("view-review","/books/"+book.getIsbn()+"/reviews/"+review.getID(),"GET"));
    	
    	return Response.ok().entity(reviewdto.getLinks()).build();
    }
    
    
    @GET
    @Path("/{isbn}/reviews/{id}")
    @Timed(name = "view-review")
    public Response viewReview(@PathParam("isbn") long isbn, @PathParam("id") long id) {
		int i=0;
		Book retrieveBook = bookRepository.getBookByISBN(isbn);

		while (retrieveBook.getbookReview(i).getID()!=id)
		{
			i++;
		}
		
		Review r= retrieveBook.getbookReview(i);
		

		ReviewDto reviewResponse = new ReviewDto(r.getRating(),r.getComment());
		LinksDto links = new LinksDto();
    	links.addLink(new LinkDto("view-review", "/books/" + retrieveBook.getIsbn() + "/reviews/" + retrieveBook.getbookReview(i).getID(), "GET"));
    	return Response.ok(links).build();
    }
   
    @GET
    @Path("/{isbn}/reviews")
    @Timed(name = "view-all-reviews")
    public ReviewsDto viewAllReviews(@PathParam("isbn") long isbn) {

		Book retrieveBook = bookRepository.getBookByISBN(isbn);
		ReviewsDto reviewResponse = new ReviewsDto(retrieveBook.getReview());

	return reviewResponse;
    }
    
    
 
}

