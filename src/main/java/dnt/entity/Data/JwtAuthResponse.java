package dnt.entity.Data;

import dnt.entity.Role;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtAuthResponse {

    @NonNull
    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private List<Role> roles;
    private String message;
}
