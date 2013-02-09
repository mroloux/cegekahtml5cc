package be.cegeka.eventualizr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity
@Table(name=Talk.TALK_TABLE_NAME)
public class Talk extends AbstractEntity {
	
	public static final String TALK_TABLE_NAME = "Talk";

	@NotNull
	@Size(max = 64)
	private String speaker;
	
	@NotNull
	@Size(max = 128)
	private String subject;
	
	@NotNull
	@Size(max = 64)
	private String location;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@Column(name="fromDateTime")
	private LocalDateTime from;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@Column(name="tillDateTime")
	private LocalDateTime till;
	
	@NotNull
	@Size(max = 300)
	private String summary;
	
	@NotNull
	@Size(max = 300)
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
