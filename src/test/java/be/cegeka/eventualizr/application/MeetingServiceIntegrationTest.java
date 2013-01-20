package be.cegeka.eventualizr.application;

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

import be.cegeka.eventualizr.domain.Meeting;
import be.cegeka.eventualizr.domain.MeetingForTests;
import be.cegeka.eventualizr.domain.Talk;
import be.cegeka.eventualizr.domain.TalkForTests;
import be.cegeka.eventualizr.web.test.infrastructure.DBSeeder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:application-context.xml",
    "classpath:datasource-test-context.xml"
})
@Transactional
public class MeetingServiceIntegrationTest {

	@Autowired
	private DBSeeder dbSeeder;
	@Autowired
	private MeetingService meetingService;
	
	private Meeting expectedMeeting1;
	private Meeting expectedMeeting2;
	private Talk talk1;
	private Talk talk2;
	
	@Before
	public void setUp() throws Exception {
		expectedMeeting1 = MeetingForTests.withDefaults(new LocalDateTime(2013, 1, 30, 16, 0), new LocalDateTime(2013, 1, 30, 20, 0));
		expectedMeeting2 = MeetingForTests.withDefaults(new LocalDateTime(2013, 1, 30, 13, 0), new LocalDateTime(2013, 1, 30, 14, 0));
		
		talk1 = TalkForTests.withDefaults(new LocalDateTime(2013, 1, 30, 16, 0), new LocalDateTime(2013, 1, 30, 18, 0));
		talk2 = TalkForTests.withDefaults(new LocalDateTime(2013, 1, 30, 18, 0), new LocalDateTime(2013, 1, 30, 20, 0));
		
		expectedMeeting1.addTalk(talk1);
		expectedMeeting1.addTalk(talk2);
		
		dbSeeder.seedData(expectedMeeting1);
	}

	@Test
	public void shouldBeAbleToFindOne() {
		Meeting actualMeeting = meetingService.findOne(expectedMeeting1.getId());
		
		assertMeeting1(actualMeeting);
	}

	
	@Test
	public void shouldBeAbleToFindAll() {
		dbSeeder.seedData(expectedMeeting2);
		List<Meeting> actualMeetings = meetingService.findAll();
		
		assertThat(actualMeetings).containsOnly(expectedMeeting1, expectedMeeting2);
		assertMeeting1(actualMeetings.get(0));
		
		assertThat(actualMeetings.get(1)).isLenientEqualsToByIgnoringFields(expectedMeeting2, "talks");
		assertThat(actualMeetings.get(1).getTalks()).isEmpty();
		
	}
	
	@Test
	public void shouldBeAbleToSave(){
		Meeting actualMeeting = meetingService.save(expectedMeeting1);
		
		assertThat(actualMeeting.getId()).isNotNull().isGreaterThan(0);
		assertThat(actualMeeting.getTalks().get(0).getId()).isNotNull().isGreaterThan(0);
		assertThat(actualMeeting.getTalks().get(1).getId()).isNotNull().isGreaterThan(0);
	}
	
	private void assertMeeting1(Meeting actualMeeting) {
		assertThat(actualMeeting).isLenientEqualsToByIgnoringFields(expectedMeeting1, "talks");
		assertThat(actualMeeting.getTalks()).containsOnly(talk1, talk2);
		assertThat(actualMeeting.getTalks().get(0)).isEqualsToByComparingFields(talk1);
		assertThat(actualMeeting.getTalks().get(1)).isEqualsToByComparingFields(talk2);
	}

}
