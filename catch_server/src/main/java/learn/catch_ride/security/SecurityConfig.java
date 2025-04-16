//package learn.catch_ride.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final JwtConverter converter;
//
//    public SecurityConfig(JwtConverter converter) {
//        this.converter = converter;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//
//        http.cors();
//
//        http.authorizeRequests() // 2
//                .antMatchers("/authenticate").permitAll()
//                .antMatchers("/refresh_token").authenticated()
//                .antMatchers(HttpMethod.GET, "/booking", "/booking/*").permitAll()
//                .antMatchers(HttpMethod.POST, "/booking").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.PUT, "/booking/*").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/booking/*").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.GET, "/dealership", "/dealership/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/dealership").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/dealership/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/dealership/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/vehicle", "/vehicle/*").permitAll()
//                .antMatchers(HttpMethod.POST, "/vehicle").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/vehicle/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/vehicle/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/location", "/location/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/location").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/location/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/location/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/user", "/user/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/user").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/user/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/user/*").hasRole("ADMIN")
//                .and()
//                .addFilter(new JwtRequestFilter(authenticationManager(), converter)) // 3
//                .sessionManagement() // 4
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//    @Override
//    @Bean
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder getEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("http://localhost:3000")
//                        .allowedMethods("*");
//            }
//        };
//    }
//}
