package be.cegeka.eventualizr.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.cegeka.eventualizr.domain.Meeting;
import be.cegeka.eventualizr.domain.MeetingRepository;


@Service
@Transactional
public class MeetingService {
	
	@Autowired
	private MeetingRepository meetingRepository;
	
	@Transactional(readOnly=true)
	public Meeting findOne(Long id) {
		return meetingRepository.findOne(id);
	}
	
	@Transactional(readOnly=true)
	public List<Meeting> findAll() {
		return meetingRepository.findAll();
	}
	
	public Meeting save(Meeting entity){
		return meetingRepository.save(entity);
	}

}
