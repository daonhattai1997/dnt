package dnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dnt.entity.Staff;
import dnt.repository.StaffRepository;
import lombok.Setter;

@Service
@Setter(onMethod = @__(@Autowired))
public class StaffService {
	private StaffRepository staffRepository;
	
	public List<Staff> getAllStaff() {
		return staffRepository.findAll();
	}

}
