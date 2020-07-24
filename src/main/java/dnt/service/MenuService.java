package dnt.service;

import dnt.entity.Data.Constants;
import dnt.entity.EnumType.MenuTypeName;
import dnt.entity.MenuProgram;
import dnt.repository.MenuRepository;
import dnt.repository.RoleRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Setter(onMethod = @__(@Autowired))
public class MenuService {

    MenuRepository menuRepository;
    RoleRepository roleRepository;

    public List<MenuProgram> findAll() {
        return menuRepository.findAll();
    }

    public List<String> getMenuLink(int roleId) {
        return menuRepository.getMenuUrlByRoles(roleId);
    }

    public List<MenuProgram> getMenuByRole(int roleId) {
        return menuRepository.findAllByRolesAndDeleteFlag(roleRepository.findByRoleId(roleId), Constants.NO);
    }


}
