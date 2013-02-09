package be.cegeka.eventualizr.domain.infrastructure;

import org.junit.rules.ExternalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.cegeka.eventualizr.domain.Meeting;
import be.cegeka.eventualizr.domain.Talk;

@Component
public class DBRule extends ExternalResource  {
	
	@Autowired
	private DBManager dbManager;
	
	private DBRule() {
	}
	
	@Override
	protected void before() throws Throwable {
		dbManager.updateDatabase();
		dbManager.truncateTables(Talk.TALK_TABLE_NAME, Meeting.MEETING_TABLE_NAME);
	}
	
	public void seedData(Object... entities) {
		dbManager.seedData(entities);
	}
	
}
