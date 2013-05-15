package be.cegeka.eventualizr.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.google.common.collect.Lists;

@Entity
@Table(name=Meeting.MEETING_TABLE_NAME)
public class Meeting extends AbstractEntity {

	public static final String MEETING_TABLE_NAME = "Meeting";

	@NotNull
	@Size(max = 64)
	private String venue;
	
	@NotNull
	@Size(max = 128)
	private String title;
	
	@Column(name="startDateTime")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime start;
	
	@Column(name="endDateTime")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime end;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(nullable=false, name="Meeting_id", updatable=false)
	private List<Talk> talks = Lists.newArrayList();

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	public List<Talk> getTalks() {
		return Collections.unmodifiableList(talks);
	}

	public void addTalk(Talk talk) {
		talks.add(talk);
	}

	public Talk getTalk(Long talkId) {
		for (Talk talk : talks) {
			if(talkId.equals(talk.getId())){
				return talk;
			}
		}
		return null;
	}

	public void removeTalk(Long talkid) {
		Talk talkToBeRemoved = null;
		for (Talk talk : getTalks()) {
			if(talk.getId() == talkid) {
				talkToBeRemoved = talk;
			}
		}
		talks.remove(talkToBeRemoved);
	}
	
}
