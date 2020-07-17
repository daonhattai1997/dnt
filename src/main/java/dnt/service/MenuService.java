package dnt.service;

import dnt.entity.Data.Constants;
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

    public List<MenuProgram> getMenuByRole(int roleId) {
        List<MenuProgram> lst = menuRepository.findAllByRolesAndDeleteFlag(roleRepository.findByRoleId(roleId), Constants.NO);

        return sortMenu(lst);
    }

    private List<MenuProgram> sortMenu(List<MenuProgram> lst) {
        return sortMenu(lst, 0);
    }

    private List<MenuProgram> sortMenu(List<MenuProgram> lst, int id) {
        List<MenuProgram> res = new ArrayList<>();

        for(MenuProgram mp: lst)
        {
            if(mp.getParentId() == id)
            {
                res.add(mp);
                res.addAll(sortMenu(lst.stream().filter(e -> e.getParentId() != id).collect(Collectors.toList()), mp.getMenuId()));
            }
        }

        return res;
    }


}
