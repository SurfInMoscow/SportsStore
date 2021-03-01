package ru.voroby.storebackend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.voroby.storebackend.AbstractMvcTest;
import ru.voroby.storebackend.dto.TokenDTO;
import ru.voroby.storebackend.dto.UserDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class AuthenticationTest extends AbstractMvcTest {

  private final String username = "admin";

  private final String password = "admin12345";

  @Test
  public void success() throws Exception {
    final var content = mvc.perform(MockMvcRequestBuilders.post("/authentication/login")
      .content(mapper.writeValueAsString(new UserDTO(username, password))))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(print()).andReturn().getResponse().getContentAsString();

    final var tokenDTO = mapper.readValue(content, TokenDTO.class);

    assertEquals(true, tokenDTO.getSuccess());
    assertNotNull(tokenDTO.getToken());
  }

  @Test
  public void failed() throws Exception {
    final var content = mvc.perform(MockMvcRequestBuilders.post("/authentication/login")
      .content(mapper.writeValueAsString(new UserDTO(username, "incorrect"))))
      .andExpect(MockMvcResultMatchers.status().is4xxClientError())
      .andDo(print());
  }
}
