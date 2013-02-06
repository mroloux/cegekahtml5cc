package be.cegeka.eventualizr.domain;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.LocalDateTime;
import org.mockito.internal.util.reflection.Whitebox;

public class MeetingForTests {

	public static Meeting withDefaults(LocalDateTime start, LocalDateTime end) {
		Meeting meeting = new Meeting();
		meeting.setTitle(RandomStringUtils.randomAlphabetic(128));
		meeting.setVenue(RandomStringUtils.randomAlphabetic(64));
		meeting.setStart(start);
		meeting.setEnd(end);
		return meeting;
	}
	
	public static Meeting withDefaults(Long id, LocalDateTime start, LocalDateTime end) {
		Meeting meeting = withDefaults(start, end);
		Whitebox.setInternalState(meeting, "id", id);
		return meeting;
	}
}
