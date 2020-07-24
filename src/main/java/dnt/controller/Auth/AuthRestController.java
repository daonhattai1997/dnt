package dnt.controller.Auth;

import dnt.config.JwtTokenProvider;
import dnt.entity.Data.JwtAuthResponse;
import dnt.entity.Staff;
import dnt.entity.UserPrincipal;
import dnt.entity.Data.ApiResponse;
import dnt.entity.Data.LoginRequest;
import dnt.entity.Data.RegisterRequest;
import dnt.service.AuthService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
@Setter(onMethod = @__(@Autowired))
public class AuthRestController {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private AuthService authService;

    @PostMapping(value = "/login", consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );

        // if there is no exception, that means user information is available
        // set authentication into Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //return jwt
        String jwt = jwtTokenProvider.generateToken(authentication);

        UserPrincipal up = (UserPrincipal) authentication.getPrincipal();
        JwtAuthResponse response = new JwtAuthResponse(jwt);
        response.setRoles(up.getStaff().getRoles());
        response.setUsername(up.getUsername());

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/register", consumes = {"application/json"})
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        Staff staff = authService.register(registerRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/users/{username}")
                .buildAndExpand(staff.getAccount().getUsername())
                .toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Dang ky thanh cong"));
    }

}
