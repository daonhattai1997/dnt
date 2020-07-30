package dnt.controller.Auth;

import dnt.config.JwtTokenProvider;
import dnt.entity.Data.*;
import dnt.entity.Staff;
import dnt.entity.UserPrincipal;
import dnt.service.AuthService;
import dnt.util.SecurityCipher;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@Setter(onMethod = @__(@Autowired))
public class AuthRestController {
    private AuthenticationManager authenticationManager;
    private AuthService authService;

    @GetMapping("/profile/me")
    public ResponseEntity<?> profilePage() {
        return ResponseEntity.ok(authService.getUserProfile());
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> login(
            @CookieValue(name = "accessToken", required = false) String accessToken,
            @CookieValue(name = "refreshToken", required = false) String refreshToken,
            @Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );

        // if there is no exception, that means user information is available
        // set authentication into Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
        String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);

        return ResponseEntity.ok(authService.login(loginRequest, decryptedAccessToken, decryptedRefreshToken));
    }

    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> refreshToken(
            @CookieValue(name = "accessToken", required = false) String accessToken,
            @CookieValue(name = "refreshToken", required = false) String refreshToken) {
        String decryptedAccessToken = SecurityCipher.decrypt(accessToken);
        String decryptedRefreshToken = SecurityCipher.decrypt(refreshToken);
        return authService.refresh(decryptedAccessToken, decryptedRefreshToken);
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
