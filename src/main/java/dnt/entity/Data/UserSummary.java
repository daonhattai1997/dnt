package dnt.entity.Data;

import dnt.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:11:39, Pzt
 **/
@Data
@Getter
@Setter
@AllArgsConstructor
public class UserSummary {
    private String username;
    private List<Role> roles;
}
