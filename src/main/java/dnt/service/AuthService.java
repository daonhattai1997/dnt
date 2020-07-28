package dnt.service;

import dnt.config.JwtTokenProvider;
import dnt.entity.Data.LoginRequest;
import dnt.entity.Data.LoginResponse;
import dnt.entity.Data.Token;
import dnt.entity.EnumType.RoleName;
import dnt.entity.Role;
import dnt.entity.Staff;
import dnt.entity.User;
import dnt.entity.UserPrincipal;
import dnt.entity.Data.RegisterRequest;
import dnt.exception.ApplicationException;
import dnt.repository.AccountRepository;
import dnt.repository.GroupRepository;
import dnt.repository.RoleRepository;
import dnt.repository.StaffRepository;
import dnt.util.CookieUtil;
import lombok.Setter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Setter(onMethod = @__(@Autowired))
public class AuthService implements UserDetailsService {

    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;
    private GroupRepository groupRepository;
    private StaffRepository staffRepository;
    private RoleRepository roleRepository;
    private JwtTokenProvider jwtTokenProvider;
    private CookieUtil cookieUtil;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User account = accountRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Khong tim thay tai khoan " + userName));
        account.getStaff().setRoles(findAllByStaffs(account.getStaff().getStaffId()));

        return UserPrincipal.create(account);
    }

    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest, String accessToken, String refreshToken) {

        String userName = loginRequest.getUsername();
        User user = accountRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Khong tim thay tai khoan " + userName));
        user.getStaff().setRoles(findAllByStaffs(user.getStaff().getStaffId()));

        Boolean accessTokenValid = jwtTokenProvider.validateToken(accessToken);
        Boolean refreshTokenValid = jwtTokenProvider.validateToken(refreshToken);

        HttpHeaders responseHeaders = new HttpHeaders();
        Token newAccessToken;
        Token newRefreshToken;
        if (!accessTokenValid && !refreshTokenValid) {
            newAccessToken = jwtTokenProvider.generateJwt(user.getUsername());
            newRefreshToken = jwtTokenProvider.refreshJwt(user.getUsername());
            addAccessTokenCookie(responseHeaders, newAccessToken);
            addRefreshTokenCookie(responseHeaders, newRefreshToken);
        }

        if (!accessTokenValid && refreshTokenValid) {
            newAccessToken = jwtTokenProvider.generateJwt(user.getUsername());
            addAccessTokenCookie(responseHeaders, newAccessToken);
        }

        if (accessTokenValid && refreshTokenValid) {
            newAccessToken = jwtTokenProvider.generateJwt(user.getUsername());
            newRefreshToken = jwtTokenProvider.refreshJwt(user.getUsername());
            addAccessTokenCookie(responseHeaders, newAccessToken);
            addRefreshTokenCookie(responseHeaders, newRefreshToken);
        }

        LoginResponse loginResponse = new LoginResponse(LoginResponse.SuccessFailure.SUCCESS, "Auth successful. Tokens are created in cookie.");
        return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);

    }

    public ResponseEntity<LoginResponse> refresh(String accessToken, String refreshToken) {
        Boolean refreshTokenValid = jwtTokenProvider.validateToken(refreshToken);
        if (!refreshTokenValid) {
            throw new IllegalArgumentException("Refresh Token is invalid!");
        }

        String currentUsername = jwtTokenProvider.getUsernameFromJWT(accessToken);

        Token newAccessToken = jwtTokenProvider.generateJwt(currentUsername);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createAccessTokenCookie(newAccessToken.getTokenValue(), newAccessToken.getDuration()).toString());

        LoginResponse loginResponse = new LoginResponse(LoginResponse.SuccessFailure.SUCCESS, "Auth successful. Tokens are created in cookie.");
        return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
    }

    private void addAccessTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createAccessTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }

    private void addRefreshTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createRefreshTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }

    public Staff register(RegisterRequest registerRequest) {
        if (accountRepository.existsByUsername(registerRequest.getUsername())) {
            throw new ApplicationException("Username da duoc su dung");
        }

        User account = new User(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()));
        final Staff staff = new Staff();

        if (registerRequest.getOwnGroupIds() != null) {
            registerRequest.getOwnGroupIds()
                    .forEach(grId -> staff.addOwn(groupRepository.findById(grId)
                            .orElseThrow(() -> new ApplicationException("Khong ton tai nhom"))));
        }
        if (registerRequest.getOwnedByGroupIds() != null) {
            registerRequest.getOwnedByGroupIds()
                    .forEach(grId -> staff.addOwnedBy(groupRepository.findById(grId)
                            .orElseThrow(() -> new ApplicationException("Khong ton tai nhom"))));
        }

        staff.setAddress(registerRequest.getAddress());
        staff.setEmail(registerRequest.getEmail());
        staff.setGender(registerRequest.getGender());
        staff.setName(registerRequest.getName());
        mapRolesFromRequest(registerRequest).forEach(staff::addRole);

        account.setToStaff(staff);
        return staffRepository.save(staff);
    }

    private Set<Role> mapRolesFromRequest(RegisterRequest registerRequest) {
        return registerRequest.getRoles().stream()
                .map(role -> {
                    log.info(RoleName.valueOf(role).toString());
                    return roleRepository.findByName(RoleName.valueOf(role))
                            .orElseThrow(() -> new ApplicationException("Khong ton tai role"));
                })
                .collect(Collectors.toSet());
    }

    public List<Role> findAllByStaffs(int staff_id) {
        return roleRepository.findAllByStaffs(staffRepository.findAllByStaffId(staff_id));
    }
}
