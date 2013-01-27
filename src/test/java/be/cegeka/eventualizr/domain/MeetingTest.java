package be.cegeka.eventualizr.domain;

import static org.fest.assertions.api.Assertions.assertThat;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

public class MeetingTest {
	
	private static final Long ID_TALK1 = 1L;
	private static final Long ID_TALK2 = 2L;
	private static final Long ID_TALK_NON_EXISTING = 3L;
	private Meeting meeting;
	private Talk talk1;
	private Talk talk2;
	

	@Before
	public void setUp()  {
		LocalDateTime start = new LocalDateTime(2013,
				1, 30, 16, 0);
		LocalDateTime end = new LocalDateTime(2013, 1, 30, 20, 0);
		meeting = MeetingForTests.withDefaults(start, end);
		talk1 = TalkForTests.withDefaults(ID_TALK1,
				new LocalDateTime(2013, 1, 30, 16, 0), new LocalDateTime(2013,
						1, 30, 18, 0));
		talk2 = TalkForTests.withDefaults(ID_TALK2,
				new LocalDateTime(2013, 1, 30, 18, 0), end);

		meeting.addTalk(talk1);
		meeting.addTalk(talk2);
	}

	@Test
	public void canGetTalkById() {
		assertThat(meeting.getTalk(ID_TALK1)).isEqualsToByComparingFields(talk1);
		assertThat(meeting.getTalk(ID_TALK2)).isEqualsToByComparingFields(talk2);
	}
	
	@Test
	public void talkByIdReturnsNullWhenNotFound(){
		assertThat(meeting.getTalk(ID_TALK_NON_EXISTING)).isNull();
	}

}
