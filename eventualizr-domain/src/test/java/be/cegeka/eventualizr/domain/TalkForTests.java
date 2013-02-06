package be.cegeka.eventualizr.domain;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.LocalDateTime;
import org.mockito.internal.util.reflection.Whitebox;

public class TalkForTests {
	
	public static Talk withDefaults(LocalDateTime from, LocalDateTime till) {
		Talk talk = new Talk();
		talk.setSpeaker(RandomStringUtils.randomAlphabetic(64));
		talk.setLocation(RandomStringUtils.randomAlphabetic(64));
		talk.setSubject(RandomStringUtils.randomAlphabetic(128));
		talk.setSummary(RandomStringUtils.randomAlphabetic(300));
		talk.setObjective(RandomStringUtils.randomAlphabetic(300));
		talk.setFrom(from);
		talk.setTill(till);
		return talk;
	}
	
	public static Talk withDefaults(Long id, LocalDateTime from, LocalDateTime till) {
		Talk talk = withDefaults(from, till);
		Whitebox.setInternalState(talk, "id", id);
		return talk;
	}

}
