package dnt.repository;

import dnt.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    /*@Query("select count(r) from Staff r")
    int countByStaffId();*/

    public Optional<Staff> findAllByStaffId(int staffId);
    public Optional<Staff> findByStaffIdAndDeleteFlagEquals(int staffId, String deleteFlag);
}
