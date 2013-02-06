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
import be.cegeka.eventualizr.application.to.MeetingTO;
import be.cegeka.eventualizr.application.to.TalkTO;

@Component
@Path("/meetings")
@Produces({ MediaType.APPLICATION_JSON})
public class MeetingResource {
	
	@Autowired
	private MeetingService meetingService;
	
	@GET 
	public List<MeetingTO> getMeetings(){
		return meetingService.getMeetings();
	}
	
	@GET @Path("{id : \\d+}")
	public MeetingTO getMeeting(@PathParam("id") Long id){
		return meetingService.getMeeting(id);
	}
	
	@POST
	public MeetingTO create(MeetingTO newMeeting){
		return meetingService.create(newMeeting);
	}
	
	/*
	 * create resources with put not supported (client has to provide the unique id)
	 */
	@PUT @Path("{id : \\d+}")
	public MeetingTO update(MeetingTO meeting){
		return meetingService.update(meeting);
	}
	
	@GET @Path("{id : \\d+}/talks")
	public List<TalkTO> getTalks(@PathParam("id") Long meetingId){
		return meetingService.getTalks(meetingId);
	}
	
	@GET @Path("{id : \\d+}/talks/{talkId : \\d+}")
	public TalkTO getTalk(@PathParam("id") Long meetingId, @PathParam("talkId") Long talkId){
		return meetingService.getTalk(meetingId, talkId);
	}
	
	@PUT @Path("{id : \\d+}/talks/{talkId : \\d+}")
	public TalkTO updateTalk(@PathParam("id") Long meetingId, TalkTO talkTO){
		return meetingService.update(meetingId, talkTO);
	}
	
	@POST  @Path("{id : \\d+}/talks")
	public TalkTO createTalk(@PathParam("id") Long meetingId, TalkTO newTalkTO){
		return meetingService.create(meetingId, newTalkTO);
	}
	
}
