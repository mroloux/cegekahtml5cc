package be.cegeka.eventualizr.web.api;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import be.cegeka.eventualizr.application.MeetingService;
import be.cegeka.eventualizr.domain.Meeting;
import be.cegeka.eventualizr.domain.MeetingForTests;
import be.cegeka.eventualizr.domain.Talk;
import be.cegeka.eventualizr.domain.TalkForTests;
import be.cegeka.eventualizr.web.test.infrastructure.JsonHelper;
import be.cegeka.eventualizr.web.test.infrastructure.SpringAwareGrizzlyWebTestContainerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerException;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;

public class MeetingResourceTest extends JerseyTest {

	@Autowired
	private MeetingService meetingService;
	private Meeting meeting1;
	private Talk talk1;
	private Talk talk2;

	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder("be.cegeka.eventualizr.web.api")
				.contextParam("contextConfigLocation",
						"classpath:application-context.xml, " +
						"classpath:datasource-test-context.xml")
				.servletClass(SpringServlet.class)
				.initParam("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig")
				.initParam("com.sun.jersey.config.property.packages", "be.cegeka.eventualizr.web.api;com.fasterxml.jackson.jaxrs")
				.contextPath("test")
				.contextListenerClass(ContextLoaderListener.class)
				.requestListenerClass(RequestContextListener.class).build();
	}
	
	protected TestContainerFactory getTestContainerFactory() throws TestContainerException {
		return new SpringAwareGrizzlyWebTestContainerFactory(this);
	}
	
	@Before
	public void setUp(){
		meeting1 = MeetingForTests.withDefaults(new LocalDateTime(2013, 01, 20, 15, 00), new LocalDateTime(2013, 01, 20, 20, 00));
		talk1 = TalkForTests.withDefaults(new LocalDateTime(2013, 01, 20, 15, 00), new LocalDateTime(2013, 01, 20, 16, 00));
		meeting1.addTalk(talk1);
		talk2 = TalkForTests.withDefaults(new LocalDateTime(2013, 01, 20, 17, 00), new LocalDateTime(2013, 01, 20, 20, 00));
		meeting1.addTalk(talk2);
	}
	
	@Test
	public void shouldBeAbleToCreateNewMeeting() throws Exception {
		
		WebResource webResource = resource();
		
		String createdMeetingJson = webResource.path("meetings").entity(JsonHelper.asJson(meeting1), MediaType.APPLICATION_JSON).post(String.class);
		
		Meeting createdMeeting = JsonHelper.fromJson(createdMeetingJson, Meeting.class);
		
		assertThat(createdMeeting).isLenientEqualsToByIgnoringFields(meeting1, "id", "talks");
		assertThat(createdMeeting.getId()).isNotNull().isGreaterThan(0L);
		assertThat(createdMeeting.getTalks()).hasSize(2);
		assertThat(createdMeeting.getTalks().get(0).getId()).isNotNull().isGreaterThan(0L);
		assertThat(createdMeeting.getTalks().get(1).getId()).isNotNull().isGreaterThan(0L);
	}
	
	@Test
	public void shouldBeAbleToUpdateMeeting() throws Exception {
		Meeting meeting = meetingService.save(meeting1);
		meeting.setTitle("new title");
		
		WebResource webResource = resource();
		
		String updatedMeetingJson = webResource.path("meetings/" + meeting.getId().intValue()).entity(JsonHelper.asJson(meeting), MediaType.APPLICATION_JSON).put(String.class);
		
		Meeting updatedMeeting = JsonHelper.fromJson(updatedMeetingJson, Meeting.class);
		
		assertThat(updatedMeeting.getTitle()).isEqualTo(meeting.getTitle());
	}
	
	@Test
	public void shouldBeAbleToFindAll() throws Exception {
		Meeting meeting2 = MeetingForTests.withDefaults(new LocalDateTime(2013, 01, 21, 20, 00), new LocalDateTime(2013, 01, 21, 22, 00));
		Talk talk3 = TalkForTests.withDefaults(new LocalDateTime(2013, 01, 21, 20, 00), new LocalDateTime(2013, 01, 21, 22, 00));
		meeting2.addTalk(talk3);
		
		meetingService.save(meeting1);
		meetingService.save(meeting2);
		
		WebResource webResource = resource();
		
		TypeReference<List<Meeting>> typeReference = new TypeReference<List<Meeting>>(){};
		
		String meetingsJson = webResource.path("meetings").accept(MediaType.APPLICATION_JSON).get(String.class);
		List<Meeting> meetings = JsonHelper.fromJson(meetingsJson, typeReference);
		
		assertThat(meetings).containsOnly(meeting1, meeting2);
		
		assertThat(meetings.get(0)).isEqualsToByComparingFields(meeting1);
		assertThat(meetings.get(0).getTalks()).containsOnly(talk1, talk2);
		assertThat(meetings.get(0).getTalks().get(0)).isEqualsToByComparingFields(talk1);
		assertThat(meetings.get(0).getTalks().get(1)).isEqualsToByComparingFields(talk2);
		
		assertThat(meetings.get(1)).isEqualsToByComparingFields(meeting2);
		assertThat(meetings.get(1).getTalks()).containsOnly(talk3);
		assertThat(meetings.get(1).getTalks().get(0)).isEqualsToByComparingFields(talk3);
		
	}
}
