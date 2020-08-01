package dnt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dnt.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {
	public Optional<Hotel> findByHotelName(String hotelName);
	
	@Query("select h.hotelName from Hotel h")
    List<String> findAllHotelName();

}
