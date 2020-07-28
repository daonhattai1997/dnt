package dnt.repository;

import dnt.entity.MenuProgram;
import dnt.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MenuRepository extends JpaRepository<MenuProgram, Long> {

    /*@Query("select count(r) from Staff r")
    int countByStaffId();*/

    //List<MenuProgram> getMenuProgramsByRoles(Optional<Role> roles);

    List<MenuProgram> findAllByRolesAndDeleteFlag(Optional<Role> roles, String deleteFlag);

    /*@Query("select count(r) from Room r")
    int countById();*/

    @Query(value =
            "select m.menu_url " +
            "from menu m, role_menu rm " +
            "where m.delete_flag = 'N' " +
            "and rm.role_id = :role_id " +
            "and m.menu_id = rm.menu_id",
            nativeQuery = true)
    List<String> getMenuUrlByRoles(@Param("role_id") int role_id);

    //List<MenuProgram> findAllByRolesAndParentIdAndDeleteFlagAndUseFlag(Optional<Role> roles, String deleteFlag, String useFlag);
}
