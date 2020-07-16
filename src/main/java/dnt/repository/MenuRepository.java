package dnt.repository;

import dnt.entity.MenuProgram;
import dnt.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MenuRepository extends JpaRepository<MenuProgram, Long> {

    /*@Query("select count(r) from Staff r")
    int countByStaffId();*/

    List<MenuProgram> getMenuProgramsByRoles(Optional<Role> lst);
}
