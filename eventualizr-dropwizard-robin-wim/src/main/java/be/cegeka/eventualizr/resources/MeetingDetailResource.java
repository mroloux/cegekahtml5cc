package be.cegeka.eventualizr.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import be.cegeka.eventualizr.api.TalkTO;
import be.cegeka.eventualizr.core.Talk;
import be.cegeka.eventualizr.db.MeetingDAO;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.yammer.dropwizard.hibernate.UnitOfWork;

@Path("/meeting/")
@Produces({ MediaType.APPLICATION_JSON})
public class MeetingDetailResource {

	private MeetingDAO meetingDao;

	public MeetingDetailResource(MeetingDAO meetingDao) {
		this.meetingDao = meetingDao;
	}

	@GET
	@Path("{id:\\d+}/talks")
	@UnitOfWork 
    public List<TalkTO> allTalksOfMeeting(@PathParam("id") Long meetingId) {
		List<Talk> talks = meetingDao.find(meetingId).talks();
		return ImmutableList.copyOf(Lists.transform(talks, toDto()));
    }

	private static final Function<Talk, TalkTO> toDto() {
		return new Function<Talk, TalkTO>() {
			public TalkTO apply(Talk talk) {
				return new TalkTO(talk.id(), talk.name());
			}
		};
	}
}
