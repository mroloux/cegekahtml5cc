package be.cegeka.eventualizr.application.infrastructure;

import static org.fest.assertions.api.Assertions.assertThat;
import be.cegeka.eventualizr.application.to.MeetingTO;
import be.cegeka.eventualizr.application.to.TalkTO;
import be.cegeka.eventualizr.domain.Meeting;
import be.cegeka.eventualizr.domain.Talk;

public class CommonAssert {
	
	public static void assertMeetingTO(MeetingTO meetingTO, Meeting meeting) {
		assertThat(meetingTO.getEnd()).isEqualTo(meeting.getEnd());
		assertThat(meetingTO.getId()).isEqualTo(meeting.getId());
		assertThat(meetingTO.getStart()).isEqualTo(meeting.getStart());
		assertThat(meetingTO.getTitle()).isEqualTo(meeting.getTitle());
		assertThat(meetingTO.getVenue()).isEqualTo(meeting.getVenue());
	}
	
	public static void assertTalkTO(TalkTO talkTO, Talk talk){
		assertThat(talkTO.getFrom()).isEqualTo(talk.getFrom());
		assertThat(talkTO.getId()).isEqualTo(talk.getId());
		assertThat(talkTO.getLocation()).isEqualTo(talk.getLocation());
		assertThat(talkTO.getObjective()).isEqualTo(talk.getObjective());
		assertThat(talkTO.getSpeaker()).isEqualTo(talk.getSpeaker());
		assertThat(talkTO.getSubject()).isEqualTo(talk.getSubject());
		assertThat(talkTO.getSummary()).isEqualTo(talk.getSummary());
		assertThat(talkTO.getTill()).isEqualTo(talk.getTill());
	}

}
