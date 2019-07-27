package rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import service.ReviewService;

@Path("/review")
public class ReviewController {

	@Inject
	private ReviewService service;
	
	@GET
	@Path("/all")
	public String getAllReviews() {
		return service.getAllReviews();
	}
	
	@GET
	@Path("/get/{id}")
	public String getReview(@PathParam("id") long id) {
		return service.getReview(id);
	}

	@POST
	@Path("/create")
	public String createReview(String review) {
		return service.createReview(review);
	}

	@DELETE
	@Path("/delete/{id}")
	public String deleteReview(@PathParam("id") long id) {
		return service.deleteReview(id);
	}

	@POST
	@Path("/update/{id}")
	public String updateReview(@PathParam("id") long id, String review) {
		return service.updateReview(id, review);
	}
	
}
