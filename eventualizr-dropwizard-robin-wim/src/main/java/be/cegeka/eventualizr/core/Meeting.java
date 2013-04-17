package be.cegeka.eventualizr.core;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;

@Entity
@Table(name = "MEETING")
@NamedQueries({
    @NamedQuery(
        name = "be.cegeka.eventualizr.core.Meeting.findAll",
        query = "SELECT m FROM Meeting m"
    )
})
public class Meeting {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(nullable=false, name="meeting_id", updatable=false)
	private List<Talk> talks = Lists.newArrayList();


    @Column(name = "name", nullable = false)
    private String name;
    
    public String name() {
		return name;
	}
    
    public long id() {
		return id;
	}

	public List<Talk> talks() {
		return talks;
	}
}
