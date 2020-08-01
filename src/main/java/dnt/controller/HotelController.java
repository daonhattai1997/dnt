package dnt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dnt.config.JwtTokenProvider;
import dnt.service.AuthService;
import dnt.service.HotelService;
import lombok.Setter;

@RestController
@RequestMapping("/hotel")
@Setter(onMethod = @__(@Autowired))
public class HotelController {
	private HotelService hotelService;
	
    @GetMapping( value = "/getAllHotelName")
	public ResponseEntity<?> getAllHotelName () {
    	return ResponseEntity.ok(hotelService.getAllHotelName());
    }
}
