package be.cegeka.eventualizr.dropwizard.resource;

import be.cegeka.eventualizr.application.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class EventualizrResource {

    @Autowired
    private MeetingService meetingService;

    public EventualizrResource() {
	}

	@GET
	public String sayHello() {
		return Integer.toString(meetingService.getMeetings().size());
	}
}
