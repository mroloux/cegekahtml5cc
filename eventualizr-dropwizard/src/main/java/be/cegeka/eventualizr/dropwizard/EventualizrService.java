package be.cegeka.eventualizr.dropwizard;

import be.cegeka.eventualizr.dropwizard.healthcheck.TemplateHealthCheck;
import be.cegeka.eventualizr.dropwizard.resource.EventualizrResource;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class EventualizrService extends Service<EventualizrConfiguration> {

	public static void main(String[] args) throws Exception {
        new EventualizrService().run(args);
    }

    @Override
    public void initialize(Bootstrap<EventualizrConfiguration> bootstrap) {
        bootstrap.setName("eventualizr");
    }

    @Override
    public void run(EventualizrConfiguration configuration, Environment environment) {
    	final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.addResource(new EventualizrResource(template, defaultName));
        environment.addHealthCheck(new TemplateHealthCheck(template));
    }

}
