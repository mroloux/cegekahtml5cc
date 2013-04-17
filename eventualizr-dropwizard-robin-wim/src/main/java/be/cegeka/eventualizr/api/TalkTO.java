package be.cegeka.eventualizr.api;

public class TalkTO {

	private long id;
	private String name;

	public TalkTO(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
