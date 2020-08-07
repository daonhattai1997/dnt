package dnt.repository;

import dnt.entity.EnumType.RoleName;
import dnt.entity.Role;
import dnt.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(RoleName roleName);

    Optional<Role> findByRoleId(int roleId);
    
    Role findById(int roleId);

    List<Role> findAllByStaffs(Optional<Staff> lst);
    
    @Query("select r.name from Role r")
    List<String> findAllRoleName();
}
