package be.cegeka.eventualizr.web.api;

import static be.cegeka.eventualizr.web.test.infrastructure.CommonAssert.assertMeetingTO;
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
import be.cegeka.eventualizr.application.mapper.MeetingMapper;
import be.cegeka.eventualizr.application.to.MeetingTO;
import be.cegeka.eventualizr.domain.Meeting;
import be.cegeka.eventualizr.domain.MeetingForTests;
import be.cegeka.eventualizr.domain.Talk;
import be.cegeka.eventualizr.domain.TalkForTests;
import be.cegeka.eventualizr.web.test.infrastructure.DBSeeder;
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
	@Autowired
	private MeetingMapper meetingMapper;
	@Autowired
	private DBSeeder dbSeeder;
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
		MeetingTO meetingTO = meetingMapper.toTO(meeting1);
		
		WebResource webResource = resource();
		
		String createdMeetingJson = webResource.path("meetings").entity(JsonHelper.asJson(meetingTO), MediaType.APPLICATION_JSON).post(String.class);
		
		MeetingTO createdMeeting = JsonHelper.fromJson(createdMeetingJson, MeetingTO.class);
		
		assertThat(createdMeeting).isLenientEqualsToByIgnoringFields(meetingTO, "id");
		assertThat(createdMeeting.getId()).isNotNull().isGreaterThan(0L);
	}
	
	@Test
	public void shouldBeAbleToUpdateMeeting() throws Exception {
		dbSeeder.seedDataTransactional(meeting1);
		MeetingTO meetingTO = meetingMapper.toTO(meeting1);
		meetingTO.setTitle("new title");
		
		WebResource webResource = resource();
		
		String updatedMeetingJson = webResource.path("meetings/" + meeting1.getId().intValue()).entity(JsonHelper.asJson(meetingTO), MediaType.APPLICATION_JSON).put(String.class);
		
		MeetingTO updatedMeetingTO = JsonHelper.fromJson(updatedMeetingJson, MeetingTO.class);
		
		assertThat(updatedMeetingTO.getTitle()).isEqualTo(meetingTO.getTitle());
	}
	
	@Test
	public void shouldBeAbleToGetMeetings() throws Exception {
		Meeting meeting2 = MeetingForTests.withDefaults(new LocalDateTime(2013, 01, 21, 20, 00), new LocalDateTime(2013, 01, 21, 22, 00));
		
		dbSeeder.seedDataTransactional(meeting1, meeting2);
		
		WebResource webResource = resource();
		
		TypeReference<List<MeetingTO>> typeReference = new TypeReference<List<MeetingTO>>(){};
		
		String meetingsJson = webResource.path("meetings").accept(MediaType.APPLICATION_JSON).get(String.class);
		List<MeetingTO> meetings = JsonHelper.fromJson(meetingsJson, typeReference);
		
		assertThat(meetings).hasSize(2);
		
		assertMeetingTO(meetings.get(0), meeting1);
		assertMeetingTO(meetings.get(1), meeting2);
		
	}
	
	@Test
	public void shouldBeAbleToGetMeeting() throws Exception {
		dbSeeder.seedDataTransactional(meeting1);
		
		WebResource webResource = resource();
		
		String meetingsJson = webResource.path("meetings/" + meeting1.getId().intValue()).accept(MediaType.APPLICATION_JSON).get(String.class);
		MeetingTO meetingTO = JsonHelper.fromJson(meetingsJson, MeetingTO.class);
		
		
		assertMeetingTO(meetingTO, meeting1);
	}
}
