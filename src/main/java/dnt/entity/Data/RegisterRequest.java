package dnt.entity.Data;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class RegisterRequest {

    private Set<Integer> ownGroupIds;

    private Set<Integer> ownedByGroupIds;

    private Set<String> roles;

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String gender;

}
