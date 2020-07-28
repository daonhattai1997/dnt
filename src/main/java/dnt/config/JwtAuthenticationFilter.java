package dnt.config;

import dnt.service.AuthService;
import dnt.util.SecurityCipher;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${auth.accessTokenCookieName}")
    private String accessTokenCookieName;

    @Value("${auth.refreshTokenCookieName}")
    private String refreshTokenCookieName;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtToken(request, true); // get jwt from request

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                String username = tokenProvider.getUsernameFromJWT(jwt); // Lấy id user from jwt
                UserDetails userDetails = authService.loadUserByUsername(username);

                if(userDetails != null)// If user is available, set information for Security Context
                {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception ex) {
            logger.error("Could not set account authentication in security context", ex);
        }
        finally {
            filterChain.doFilter(request, response);
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        //check Header Authorization contains jwt or not
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return SecurityCipher.decrypt(bearerToken.substring(7));
        }
        return null;
    }

    private String getJwtFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (accessTokenCookieName.equals(cookie.getName())) {
                String accessToken = cookie.getValue();
                if (accessToken == null) return null;

                return SecurityCipher.decrypt(accessToken);
            }
        }
        return null;
    }

    private String getJwtToken(HttpServletRequest request, boolean fromCookie) {
        if (fromCookie) return getJwtFromCookie(request);

        return getJwtFromRequest(request);
    }
}
