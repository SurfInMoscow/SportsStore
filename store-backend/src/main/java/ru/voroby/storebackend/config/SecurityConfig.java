package ru.voroby.storebackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  public static final String SECRET = "SportsStore";
  public static final long EXPIRATION_TIME = 3_600_000; // 1 hour
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    final var authenticationFilter = new JWTAuthenticationFilter(authenticationManager());
    authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/authentication/login", "POST"));
    http.cors().and().csrf().disable().authorizeRequests()
      .antMatchers("/products").permitAll()
      .anyRequest().authenticated()
      .and()
      .addFilter(authenticationFilter)
      .addFilter(new JWTAuthorizationFilter(authenticationManager()))
      // this disables session creation on Spring Security
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
      .passwordEncoder(passwordEncoder())
      .withUser("admin")
      .password(passwordEncoder().encode("admin12345"))
      .roles("ADMIN", "USER");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/authentication/login", new CorsConfiguration().applyPermitDefaultValues());

    return source;
  }
}
