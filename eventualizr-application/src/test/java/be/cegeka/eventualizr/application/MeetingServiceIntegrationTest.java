package be.cegeka.eventualizr.application;

import static be.cegeka.eventualizr.application.infrastructure.CommonAssert.assertMeetingTO;
import static be.cegeka.eventualizr.application.infrastructure.CommonAssert.assertTalkTO;
import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import be.cegeka.eventualizr.application.to.MeetingTO;
import be.cegeka.eventualizr.application.to.TalkTO;
import be.cegeka.eventualizr.domain.Meeting;
import be.cegeka.eventualizr.domain.MeetingForTests;
import be.cegeka.eventualizr.domain.MeetingRepository;
import be.cegeka.eventualizr.domain.Talk;
import be.cegeka.eventualizr.domain.TalkForTests;
import be.cegeka.eventualizr.domain.infrastructure.DBSeeder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:domain-context.xml", "classpath:application-context.xml",
		"classpath:datasource-test-context.xml" })
@Transactional
public class MeetingServiceIntegrationTest {

	@Autowired
	private DBSeeder dbSeeder;
	@Autowired
	private MeetingService meetingService;
	@Autowired
	private MeetingRepository meetingRepository;

	private Meeting expectedMeeting1;
	private Meeting expectedMeeting2;
	private Talk talk1;
	private Talk talk2;
	private LocalDateTime start;
	private LocalDateTime end;

	@Before
	public void setUp() throws Exception {
		start = new LocalDateTime(2013,
				1, 30, 16, 0);
		end = new LocalDateTime(2013, 1, 30, 20, 0);
		expectedMeeting1 = MeetingForTests.withDefaults(start, end);
		expectedMeeting2 = MeetingForTests.withDefaults(new LocalDateTime(2013,
				1, 30, 13, 0), new LocalDateTime(2013, 1, 30, 14, 0));

		talk1 = TalkForTests.withDefaults(
				new LocalDateTime(2013, 1, 30, 16, 0), new LocalDateTime(2013,
						1, 30, 18, 0));
		talk2 = TalkForTests.withDefaults(
				new LocalDateTime(2013, 1, 30, 18, 0), end);

		expectedMeeting1.addTalk(talk1);
		expectedMeeting1.addTalk(talk2);

		dbSeeder.seedData(expectedMeeting1);
	}

	@Test
	public void shouldBeAbleToGetMeeting() {
		MeetingTO actualMeeting = meetingService
				.getMeeting(expectedMeeting1.getId());

		assertMeetingTO(actualMeeting, expectedMeeting1);
	}

	@Test
	public void shouldBeAbleToGetMeetings() {
		dbSeeder.seedData(expectedMeeting2);
		List<MeetingTO> meetings = meetingService.getMeetings();
		
		assertThat(meetings).hasSize(2);
		assertMeetingTO(meetings.get(0), expectedMeeting1);
		assertMeetingTO(meetings.get(1), expectedMeeting2);
	}
	
	@Test
	public void shouldBeAbleToGetTalk() {
		TalkTO talk = meetingService.getTalk(expectedMeeting1.getId(), talk1.getId());
		
		assertTalkTO(talk, talk1);
	}
	
	@Test
	public void shouldBeAbleToTalks() {
		List<TalkTO> talks = meetingService.getTalks(expectedMeeting1.getId());
		
		assertThat(talks).hasSize(2);
		assertTalkTO(talks.get(0), talk1);
		assertTalkTO(talks.get(1), talk2);
	}


	@Test
	public void shouldBeAbleToUpdateMeeting() {
		MeetingTO meetingTO = meetingService
				.getMeeting(expectedMeeting1.getId());
		String newTitle = "new Title";
		meetingTO.setTitle(newTitle);
		
		MeetingTO actualMeetingTO = meetingService.update(meetingTO);

		assertThat(actualMeetingTO).isEqualsToByComparingFields(meetingTO);
		
		Meeting meeting = meetingRepository.findOne(actualMeetingTO.getId());
		assertThat(meeting.getTitle()).isEqualTo(newTitle);
	}
	
	@Test
	public void shouldBeAbleToUpdateTalk() {
		TalkTO talkTO = meetingService
				.getTalk(expectedMeeting1.getId(), talk1.getId());
		String newSubject = "new Subject";
		talkTO.setSubject(newSubject);
		
		TalkTO actualTalkTO = meetingService.update(expectedMeeting1.getId(), talkTO);

		assertThat(actualTalkTO).isEqualsToByComparingFields(talkTO);
		
		Meeting meeting = meetingRepository.findOne(expectedMeeting1.getId());
		assertThat(meeting.getTalk(actualTalkTO.getId()).getSubject()).isEqualTo(newSubject);
	}
	
	@Test
	public void shouldBeAbleToCreateMeeting() {
		MeetingTO meetingTO = new MeetingTO();
		meetingTO.setTitle("title");
		meetingTO.setVenue("venue");
		meetingTO.setEnd(end.plusMonths(2));
		meetingTO.setStart(start.plusMonths(2));
		
		MeetingTO actualMeetingTO = meetingService.create(meetingTO);
		
		assertThat(actualMeetingTO).isLenientEqualsToByIgnoringFields(meetingTO, "id");
		assertThat(actualMeetingTO.getId()).isNotNull().isGreaterThan(0L);
		
		Meeting meeting = meetingRepository.findOne(actualMeetingTO.getId());
		assertThat(meeting).isNotNull();
	}
	
	@Test
	public void shouldBeAbleToCreateTalk() {
		TalkTO talkTO = createTalkTO();
		
		TalkTO actualTalkTO = meetingService.create(expectedMeeting1.getId(), talkTO);
		
		assertThat(actualTalkTO).isLenientEqualsToByIgnoringFields(talkTO, "id");
		assertThat(actualTalkTO.getId()).isNotNull().isGreaterThan(0L);
		
		Meeting meeting = meetingRepository.findOne(expectedMeeting1.getId());
		assertThat(meeting.getTalk(actualTalkTO.getId())).isNotNull();
	}
	
	private TalkTO createTalkTO() {
		TalkTO talkTO = new TalkTO();
		talkTO.setFrom(start.plusDays(10));
		talkTO.setLocation("TO location");
		talkTO.setObjective("TO objective");
		talkTO.setSpeaker("TO speaker");
		talkTO.setSubject("TO subject");
		talkTO.setSummary("TO summary");
		talkTO.setTill(end.plusDays(15));
		return talkTO;
	}

}
