package dnt.config;

import dnt.service.AccountService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//@Configuration
@EnableWebSecurity
@Setter(onMethod = @__(@Autowired))
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountService accountService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        String[] ignored = {
                "/WEB-INF/views/**",
                "/resources/**",
                "/static/**",
                "/uploads/**",
                "/css/**",
                "/fonts/**",
                "/cms/**",
                "/js/**",
                "/images/**",
                "/assets/**"
        };
        web.ignoring().antMatchers(ignored);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] ignored = {
                "/", "/home",
                "/auth/login",
                "/auth/register"
        };

        http.cors().and() //prevent any request from another domain
//                .csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()//throw Exception
//                .sessionManagement()                                                    //***************
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()           //***************
                .authorizeRequests()
                .antMatchers(ignored).permitAll() //  ignore authentication in request ignored[]
                .antMatchers(HttpMethod.POST, "/login").permitAll() // ignore authentication for request POST to "/login"
                .anyRequest().authenticated() // the others request have to be authenticated
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
