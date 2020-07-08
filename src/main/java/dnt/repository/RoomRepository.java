package dnt.repository;

import dnt.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    /*@Query("select count(r) from Room r")
    int countById();*/
}
