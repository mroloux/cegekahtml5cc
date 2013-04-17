package be.cegeka.eventualizr.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TALK")
public class Talk {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name="name", nullable=false)
	private String name;

	public long id() {
		return id;
	}

	public String name() {
		return name;
	}

}
