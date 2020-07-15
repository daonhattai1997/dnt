package dnt.repository;

import dnt.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    /*@Query("select count(r) from Staff r")
    int countByStaffId();*/
}
