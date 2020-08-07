package dnt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dnt.service.StaffService;
import lombok.Setter;

@RestController
@RequestMapping("/staff")
@Setter(onMethod = @__(@Autowired))
public class StaffController {

	private StaffService staffService;
	
	@GetMapping(value = "/getAllStaff")
	public ResponseEntity<?> getAllStaff(){
		return ResponseEntity.ok(staffService.getAllStaff());
	}
}
