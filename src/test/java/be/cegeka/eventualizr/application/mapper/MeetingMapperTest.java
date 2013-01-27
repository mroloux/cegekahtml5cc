package be.cegeka.eventualizr.application.mapper;

import static org.fest.assertions.api.Assertions.assertThat;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import be.cegeka.eventualizr.application.to.MeetingTO;
import be.cegeka.eventualizr.application.to.TalkTO;
import be.cegeka.eventualizr.domain.Meeting;
import be.cegeka.eventualizr.domain.MeetingForTests;
import be.cegeka.eventualizr.domain.Talk;
import be.cegeka.eventualizr.domain.TalkForTests;

public class MeetingMapperTest {

	private MeetingMapper mapper;
	private Meeting meeting;
	private LocalDateTime start;
	private LocalDateTime end;
	private Long id = 15L;
	private Talk talk;

	@Before
	public void setUp() {
		mapper = new MeetingMapper();
		start = new LocalDateTime(2012, 1, 20, 15, 20);
		end = new LocalDateTime(2012, 1, 20, 17, 00);
		
		meeting = MeetingForTests.withDefaults(id , start, end);
		talk = TalkForTests.withDefaults(20L, start.plusMonths(2), end.plusMonths(2));
		meeting.addTalk(talk);
	}

	@Test
	public void canMergeEntity() {
		MeetingTO meetingTO = createMeetingTO();
		
		mapper.mergeEntity(meeting, meetingTO);
		
		assertMergedEntity(meeting, meetingTO);
	}

	private MeetingTO createMeetingTO() {
		MeetingTO meetingTO = new MeetingTO();
		meetingTO.setEnd(end.plusDays(5));
		meetingTO.setStart(start.plusDays(5));
		meetingTO.setTitle("TO title");
		meetingTO.setVenue("TO venue");
		meetingTO.setId(meeting.getId() + 1L);
		return meetingTO;
	}

	@Test
	public void canMapToMeetingTO() {
		MeetingTO to = mapper.toTO(meeting);

		assertThat(to.getId()).isEqualTo(meeting.getId());
		assertThat(to.getEnd()).isEqualTo(meeting.getEnd());
		assertThat(to.getStart()).isEqualTo(meeting.getStart());
		assertThat(to.getTitle()).isEqualTo(meeting.getTitle());
		assertThat(to.getVenue()).isEqualTo(meeting.getVenue());
	}
	
	@Test
	public void canMapToTalkTO() {
		TalkTO to = mapper.toTO(talk);
		
		assertThat(to.getId()).isEqualTo(talk.getId());
		assertThat(to.getFrom()).isEqualTo(talk.getFrom());
		assertThat(to.getLocation()).isEqualTo(talk.getLocation());
		assertThat(to.getObjective()).isEqualTo(talk.getObjective());
		assertThat(to.getSpeaker()).isEqualTo(talk.getSpeaker());
		assertThat(to.getSubject()).isEqualTo(talk.getSubject());
		assertThat(to.getSummary()).isEqualTo(talk.getSummary());
		assertThat(to.getTill()).isEqualTo(talk.getTill());
	}
	
	@Test
	public void canMapToEntity() {
		MeetingTO meetingTO = createMeetingTO();
		
		Meeting entity = mapper.toEntity(meetingTO);
		
		assertThat(entity.getId()).isNull();
		assertThat(entity.getEnd()).isEqualTo(meetingTO.getEnd());
		assertThat(entity.getStart()).isEqualTo(meetingTO.getStart());
		assertThat(entity.getTitle()).isEqualTo(meetingTO.getTitle());
		assertThat(entity.getVenue()).isEqualTo(meetingTO.getVenue());
		assertThat(entity.getTalks()).isEmpty();
	}
	
	private void assertMergedEntity(Meeting meeting, MeetingTO meetingTO) {
		assertThat(meeting.getEnd()).isEqualTo(meetingTO.getEnd());
		assertThat(meeting.getId()).isEqualTo(id);
		assertThat(meeting.getStart()).isEqualTo(meetingTO.getStart());
		assertThat(meeting.getTitle()).isEqualTo(meetingTO.getTitle());
		assertThat(meeting.getVenue()).isEqualTo(meetingTO.getVenue());
		assertThat(meeting.getTalks()).containsExactly(talk);
		assertThat(meeting.getTalks().get(0)).isEqualsToByComparingFields(talk);
	}

}
