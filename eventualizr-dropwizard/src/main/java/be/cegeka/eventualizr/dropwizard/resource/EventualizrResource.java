package be.cegeka.eventualizr.dropwizard.resource;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import be.cegeka.eventualizr.dropwizard.model.Saying;

import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class EventualizrResource {
	private final String template;
	private final String defaultName;
	private final AtomicLong counter;

	public EventualizrResource(String template, String defaultName) {
		this.template = template;
		this.defaultName = defaultName;
		this.counter = new AtomicLong();
	}

	@GET
	@Timed
	public Saying sayHello(@QueryParam("name") Optional<String> name) {
		return new Saying(counter.incrementAndGet(), String.format(template,
				name.or(defaultName)));
	}
}
