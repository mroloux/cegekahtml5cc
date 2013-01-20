package be.cegeka.eventualizr.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.cegeka.eventualizr.application.mapper.MeetingMapper;
import be.cegeka.eventualizr.application.to.MeetingTO;
import be.cegeka.eventualizr.domain.Meeting;
import be.cegeka.eventualizr.domain.MeetingRepository;

import com.google.common.collect.Lists;


@Service
@Transactional
public class MeetingService {
	
	@Autowired
	private MeetingRepository meetingRepository;
	@Autowired
	private MeetingMapper meetingMapper;
	
	@Transactional(readOnly=true)
	public MeetingTO getMeeting(Long id) {
		return meetingMapper.toTO(meetingRepository.findOne(id));
	}
	
	@Transactional(readOnly=true)
	public List<MeetingTO> getMeetings() {
		List<MeetingTO> meetingTOs = Lists.newArrayList();
		for (Meeting meeting : meetingRepository.findAll()) {
			meetingTOs.add(meetingMapper.toTO(meeting));
		}
		return meetingTOs;
	}
	
	public MeetingTO update(MeetingTO meetingTO){
		Meeting meeting = meetingRepository.findOne(meetingTO.getId());
		meetingMapper.mergeEntity(meeting, meetingTO);
		return meetingMapper.toTO(meetingRepository.save(meeting));
	}
	
	public MeetingTO create(MeetingTO meetingTO){
		Meeting meeting = meetingMapper.toEntity(meetingTO);
		return meetingMapper.toTO(meetingRepository.save(meeting));
	}

}
