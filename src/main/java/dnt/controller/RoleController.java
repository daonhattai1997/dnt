package dnt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dnt.service.RoleService;
import lombok.Setter;

@RestController
@RequestMapping("/role")
@Setter(onMethod = @__(@Autowired))
public class RoleController {
	
	private RoleService roleService;
	
	@GetMapping(value = "/getAllRole")
	public ResponseEntity<?> getAllRole() {
		return ResponseEntity.ok(roleService.getAllRole());
	}

}
