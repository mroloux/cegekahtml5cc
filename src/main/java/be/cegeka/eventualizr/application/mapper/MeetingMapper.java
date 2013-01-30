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

	public Talk toEntity(TalkTO talkTO) {
		Talk entity = new Talk();
		entity.setFrom(talkTO.getFrom());
		entity.setLocation(talkTO.getLocation());
		entity.setObjective(talkTO.getObjective());
		entity.setSpeaker(talkTO.getSpeaker());
		entity.setSubject(talkTO.getSubject());
		entity.setSummary(talkTO.getSummary());
		entity.setTill(talkTO.getTill());
		entity.setFrom(talkTO.getFrom());
		entity.setFrom(talkTO.getFrom());
		return entity;
	}

	public void mergeEntity(Talk talk, TalkTO talkTO) {
		talk.setFrom(talkTO.getFrom());
		talk.setLocation(talkTO.getLocation());
		talk.setObjective(talkTO.getObjective());
		talk.setSpeaker(talkTO.getSpeaker());
		talk.setSubject(talkTO.getSubject());
		talk.setSummary(talkTO.getSummary());
		talk.setTill(talkTO.getTill());
		
	}
	
}
