package dnt.repository;

import dnt.entity.EnumType.RoleName;
import dnt.entity.Role;
import dnt.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);

    Optional<Role> findByRoleId(int roleId);

    Optional<Role> findByRoleIdIn(List<String> lstId);

    List<Role> findAllByStaffs(Optional<Staff> lst);
}
