package be.cegeka.eventualizr.web.test.infrastructure;

import static org.fest.assertions.api.Assertions.assertThat;
import be.cegeka.eventualizr.application.to.MeetingTO;
import be.cegeka.eventualizr.domain.Meeting;

public class CommonAssert {
	
	public static void assertMeetingTO(MeetingTO meetingTO, Meeting meeting) {
		assertThat(meetingTO.getEnd()).isEqualTo(meeting.getEnd());
		assertThat(meetingTO.getId()).isEqualTo(meeting.getId());
		assertThat(meetingTO.getStart()).isEqualTo(meeting.getStart());
		assertThat(meetingTO.getTitle()).isEqualTo(meeting.getTitle());
		assertThat(meetingTO.getVenue()).isEqualTo(meeting.getVenue());
	}

}
