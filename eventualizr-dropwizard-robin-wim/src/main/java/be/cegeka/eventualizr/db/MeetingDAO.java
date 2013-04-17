package be.cegeka.eventualizr.db;

import java.util.List;

import org.hibernate.SessionFactory;

import be.cegeka.eventualizr.core.Meeting;

import com.yammer.dropwizard.hibernate.AbstractDAO;

public class MeetingDAO extends AbstractDAO<Meeting> {

	public MeetingDAO(SessionFactory factory) {
		super(factory);
	}

	public List<Meeting> findAll() {
		return list(namedQuery("be.cegeka.eventualizr.core.Meeting.findAll"));
	}
	
	public Meeting find(long meetingId) {
		Meeting meeting = get(meetingId);
		return meeting;
	}
}
