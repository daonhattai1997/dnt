package dnt.service;

import dnt.entity.MenuProgram;
import dnt.repository.MenuRepository;
import dnt.repository.RoleRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Setter(onMethod = @__(@Autowired))
public class MenuService {

    MenuRepository menuRepository;
    RoleRepository roleRepository;

    public List<MenuProgram> findAll() {
        return menuRepository.findAll();
    }

    public List<MenuProgram> getMenuByRole(int roleId) {
        return menuRepository.getMenuProgramsByRoles(roleRepository.findAllByRoleId(roleId));
    }
}
