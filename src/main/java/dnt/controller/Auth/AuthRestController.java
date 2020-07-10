package dnt.controller.Auth;

import dnt.config.JwtTokenProvider;
import dnt.entity.Data.JwtAuthResponse;
import dnt.entity.Staff;
import dnt.entity.UserPrincipal;
import dnt.entity.Data.ApiResponse;
import dnt.entity.Data.LoginRequest;
import dnt.entity.Data.RegisterRequest;
import dnt.service.AccountService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
@Setter(onMethod = @__(@Autowired))
public class AuthRestController {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private AccountService accountService;

    @PostMapping(value = "/login", consumes = {"application/json"})
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        JwtAuthResponse response = new JwtAuthResponse(jwt);

        response.setRoles(((UserPrincipal) authentication.getPrincipal()).getStaff().getRoles());

        return ResponseEntity.ok(new JwtAuthResponse(jwt));
    }

    @PostMapping(value = "/register", consumes = {"application/json"})
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        Staff staff = accountService.register(registerRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/users/{username}")
                .buildAndExpand(staff.getAccount().getUsername())
                .toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Dang ky thanh cong"));
    }

}
