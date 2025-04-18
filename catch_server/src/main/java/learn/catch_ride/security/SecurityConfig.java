package learn.catch_ride.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.cors();

        http.authorizeRequests() // 2
                .antMatchers("/api/auth/authenticate").permitAll()
                .antMatchers("/api/auth/register").permitAll()
                .antMatchers("/refresh_token").authenticated()
                .antMatchers(HttpMethod.GET, "/api/booking", "/booking/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/booking").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/booking/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/booking/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/dealership", "/api/dealership/**", "/api/dealership/id/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/dealership").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/dealership/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/dealership/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/vehicle", "/api/vehicle/**", "/api/dealership/id/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/vehicle").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/vehicle/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/vehicle/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/location", "/location/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/location").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/location/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/location/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user", "/user/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/user/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .addFilter(new JwtRequestFilter(authenticationManager(), converter)) // 3
                .sessionManagement() // 4
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("*");
            }
        };
    }
}