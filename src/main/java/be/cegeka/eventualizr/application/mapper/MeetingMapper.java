package be.cegeka.eventualizr.application.mapper;

import org.springframework.stereotype.Component;

import be.cegeka.eventualizr.application.to.MeetingTO;
import be.cegeka.eventualizr.application.to.TalkTO;
import be.cegeka.eventualizr.domain.Meeting;
import be.cegeka.eventualizr.domain.Talk;

@Component
public class MeetingMapper {
	
	public MeetingTO toTO(Meeting meeting){
		MeetingTO to = new MeetingTO();
		to.setEnd(meeting.getEnd());
		to.setId(meeting.getId());
		to.setStart(meeting.getStart());
		to.setTitle(meeting.getTitle());
		to.setVenue(meeting.getVenue());
		return to;
	}

	public void mergeEntity(Meeting meeting, MeetingTO meetingTO) {
		meeting.setEnd(meetingTO.getEnd());
		meeting.setStart(meetingTO.getStart());
		meeting.setTitle(meetingTO.getTitle());
		meeting.setVenue(meetingTO.getVenue());
	}

	public Meeting toEntity(MeetingTO meetingTO) {
		Meeting meeting = new Meeting();
		meeting.setEnd(meetingTO.getEnd());
		meeting.setStart(meetingTO.getStart());
		meeting.setTitle(meetingTO.getTitle());
		meeting.setVenue(meetingTO.getVenue());
		return meeting;
	}

	public TalkTO toTO(Talk talk) {
		TalkTO to = new TalkTO();
		to.setFrom(talk.getFrom());
		to.setId(talk.getId());
		to.setLocation(talk.getLocation());
		to.setObjective(talk.getObjective());
		to.setSpeaker(talk.getSpeaker());
		to.setSubject(talk.getSubject());
		to.setSummary(talk.getSummary());
		to.setTill(talk.getTill());
		return to;
	}

}
