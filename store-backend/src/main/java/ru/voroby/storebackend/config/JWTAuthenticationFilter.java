package ru.voroby.storebackend.config;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.voroby.storebackend.dto.UserDTO;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static ru.voroby.storebackend.config.SecurityConfig.EXPIRATION_TIME;
import static ru.voroby.storebackend.config.SecurityConfig.HEADER_STRING;
import static ru.voroby.storebackend.config.SecurityConfig.SECRET;
import static ru.voroby.storebackend.config.SecurityConfig.TOKEN_PREFIX;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
    try {
      var userDTO = new ObjectMapper().readValue(req.getInputStream(), UserDTO.class);

      return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          userDTO.getUsername(),
          userDTO.getPassword(),
          new ArrayList<>())
      );
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                          FilterChain chain, Authentication authResult) {
    String token = JWT.create()
      .withSubject(((User) authResult.getPrincipal()).getUsername())
      .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .sign(HMAC512(SECRET.getBytes()));
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
  }


}
