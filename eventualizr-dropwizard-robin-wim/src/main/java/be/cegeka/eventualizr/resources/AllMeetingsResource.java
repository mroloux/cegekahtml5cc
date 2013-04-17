package be.cegeka.eventualizr.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import be.cegeka.eventualizr.api.MeetingTO;
import be.cegeka.eventualizr.core.Meeting;
import be.cegeka.eventualizr.db.MeetingDAO;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.yammer.dropwizard.hibernate.UnitOfWork;

@Path("/meetings")
@Produces({ MediaType.APPLICATION_JSON})
public class AllMeetingsResource {
	
	private MeetingDAO meetingDao;

	public AllMeetingsResource(MeetingDAO meetingDao) {
		this.meetingDao = meetingDao;
	}

	@GET
	@UnitOfWork
    public List<MeetingTO> all() {
		return Lists.transform(meetingDao.findAll(), toDTO());
    }

	private static final Function<Meeting, MeetingTO> toDTO() {
		return new Function<Meeting, MeetingTO>() {

			public MeetingTO apply(Meeting meeting) {
				return new MeetingTO(meeting.id(), meeting.name());
			}
		};
	}
}
