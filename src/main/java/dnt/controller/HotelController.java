package dnt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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