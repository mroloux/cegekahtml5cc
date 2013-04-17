package be.cegeka.eventualizr.health;

import com.yammer.metrics.core.HealthCheck;

public class EventualizrHealthCheck extends HealthCheck {

	public EventualizrHealthCheck() {
		super("noCheck");
	}

	@Override
	protected Result check() throws Exception {
		return Result.healthy();
	}
}
