package dnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dnt.entity.Role;
import dnt.repository.RoleRepository;
import lombok.Setter;

@Service
@Setter(onMethod = @__(@Autowired))
public class RoleService {

	private RoleRepository roleRepository;
	
	public List<Role> getAllRole() {
		return roleRepository.findAll();
	}
}
