package be.cegeka.eventualizr.api;

public class MeetingTO {

	private Long id;
	private String name;

	public MeetingTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}
}
