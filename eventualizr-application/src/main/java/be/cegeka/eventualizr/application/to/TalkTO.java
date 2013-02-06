package be.cegeka.eventualizr.application.to;

import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.LocalDateTime;

@XmlRootElement
public class TalkTO extends AbstractTO {
	
	private String speaker;
	
	private String subject;
	
	private String location;
	
	private LocalDateTime from;
	
	private LocalDateTime till;
	
	private String summary;
	
	private String objective;

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDateTime getFrom() {
		return from;
	}

	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	public LocalDateTime getTill() {
		return till;
	}

	public void setTill(LocalDateTime till) {
		this.till = till;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}
	
}
