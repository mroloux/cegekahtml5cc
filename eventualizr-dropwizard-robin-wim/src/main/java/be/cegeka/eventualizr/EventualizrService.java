package be.cegeka.eventualizr;


import be.cegeka.eventualizr.core.Meeting;
import be.cegeka.eventualizr.core.Talk;
import be.cegeka.eventualizr.db.MeetingDAO;
import be.cegeka.eventualizr.health.EventualizrHealthCheck;
import be.cegeka.eventualizr.resources.AllMeetingsResource;
import be.cegeka.eventualizr.resources.MeetingDetailResource;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;

public class EventualizrService extends Service<EventualizrConfiguration> {

	private HibernateBundle<EventualizrConfiguration> hibernate = new HibernateBundle<EventualizrConfiguration>(Meeting.class, Talk.class) {
	    public DatabaseConfiguration getDatabaseConfiguration(EventualizrConfiguration configuration) {
	        return configuration.database();
	    }
	};
            
    public static void main(String[] args) throws Exception {
        new EventualizrService().run(args);
    }

    @Override
    public void initialize(Bootstrap<EventualizrConfiguration> bootstrap) {
        bootstrap.setName("Eventualizr");
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(EventualizrConfiguration configuration, Environment environment) throws ClassNotFoundException {
    	System.out.println(
    			configuration.database().getUser() + "\n" +
    					configuration.database().getPassword() + "\n" +
    					configuration.database().getUrl());
    	
    	System.out.println(hibernate.getDatabaseConfiguration(configuration));
    	System.out.println(hibernate.getSessionFactory());
    	
    	final MeetingDAO meetingDao = new MeetingDAO(hibernate.getSessionFactory());
        
        environment.addResource(new AllMeetingsResource(meetingDao));
        environment.addResource(new MeetingDetailResource(meetingDao));
        
        environment.addHealthCheck(new EventualizrHealthCheck());
    }
}