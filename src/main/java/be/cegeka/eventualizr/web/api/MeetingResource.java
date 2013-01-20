package be.cegeka.eventualizr.web.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.cegeka.eventualizr.application.MeetingService;
import be.cegeka.eventualizr.domain.Meeting;

@Component
@Path("/meetings")
@Produces({ MediaType.APPLICATION_JSON})
public class MeetingResource {
	
	@Autowired
	private MeetingService meetingService;
	
	@GET 
	public List<Meeting> findAll(){
		return meetingService.findAll();
	}
	
	@GET @Path("{id : \\d+}")
	public Meeting findOne(@PathParam("id") Long id){
		return meetingService.findOne(id);
	}
	
	@POST
	public Meeting create(Meeting newMeeting){
		return meetingService.save(newMeeting);
	}
	
	@PUT @Path("{id : \\d+}")
	public Meeting update(Meeting meeting){
		return meetingService.save(meeting);
	}
	
}
