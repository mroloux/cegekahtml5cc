package be.cegeka.eventualizr.dropwizard;

import be.cegeka.eventualizr.dropwizard.resource.EventualizrConfiguration;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import org.reflections.Reflections;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.Path;
import java.util.Set;

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
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "domain-context.xml",
                "application-context.xml",
                "datasource-context.xml",
                "dropwizard-context.xml"
        );
        Set<Class<?>> resourceClasses = new Reflections("be.cegeka.eventualizr.dropwizard").getTypesAnnotatedWith(Path.class);
        for (Class<?> resource : resourceClasses) {
            environment.addResource(applicationContext.getBean(resource));
        }
    }

}
