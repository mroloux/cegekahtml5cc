package be.cegeka.eventualizr.domain;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface MeetingRepository extends Repository<Meeting, Long> {

	List<Meeting> findAll();
	
	Meeting findOne(Long id);
	
	Meeting save(Meeting entity);
}
