package be.cegeka.eventualizr.application.to;

import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.LocalDateTime;

@XmlRootElement
public class MeetingTO extends AbstractTO {
	
	private String venue;
	
	private String title;
	
	private LocalDateTime start;
	
	private LocalDateTime end;

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
	
}
