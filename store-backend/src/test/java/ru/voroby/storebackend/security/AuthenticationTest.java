package ru.voroby.storebackend.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

//TODO
@WebMvcTest
public class AuthenticationTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void success() {

  }

  @Test
  public void failed() {

  }
}
