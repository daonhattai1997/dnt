package dnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dnt.repository.HotelRepository;
import dnt.repository.MenuRepository;
import dnt.repository.RoleRepository;
import lombok.Setter;

@Service
@Setter(onMethod = @__(@Autowired))
public class HotelService {

	private HotelRepository hotelRepository;
	
	public List<String> getAllHotelName() {
		return hotelRepository.findAllHotelName();
	}
}
