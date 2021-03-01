package ru.voroby.storebackend.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.voroby.storebackend.AbstractMvcTest;
import ru.voroby.storebackend.dto.CartDTO;
import ru.voroby.storebackend.dto.CartLineDTO;
import ru.voroby.storebackend.dto.OrderDTO;
import ru.voroby.storebackend.dto.ProductDTO;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class OrderControllerTest extends AbstractMvcTest {

  @MockBean
  private OrderController orderController;

  private String URL_PATH = "/orders";

  private static OrderDTO orderDTO;

  @BeforeAll
  static void before() {
    ProductDTO productDTO = new ProductDTO(1, "name", "sport", "super", 50.0);
    CartLineDTO cartLineDTO = new CartLineDTO(3, productDTO);
    CartDTO cartDTO = new CartDTO(Set.of(cartLineDTO), 3, 150.0);
    orderDTO = new OrderDTO(1, "name", "address", "moscow", "mo", "12345", "russia", true, cartDTO);
  }

  @Test
  @WithMockUser(value = "admin", roles = {"ADMIN"})
  public void update() throws Exception {
    when(orderController.dtoToJpaEntity(any())).thenReturn(orderDTO.toOrder());
    when(orderDAO.save(any())).thenReturn(orderDTO.toOrder());

    String content = mapper.writeValueAsString(orderDTO);

    mvc.perform(MockMvcRequestBuilders.put(URL_PATH)
      .content(content).contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @WithMockUser(value = "admin", roles = {"ADMIN"})
  public void delete() throws Exception {
    doNothing().when(orderDAO).deleteById(1);

    mvc.perform(MockMvcRequestBuilders.delete(URL_PATH + "/1"))
      .andExpect(MockMvcResultMatchers.status().isAccepted());
  }

}
