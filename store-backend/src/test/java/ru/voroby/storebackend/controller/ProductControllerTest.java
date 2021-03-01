package ru.voroby.storebackend.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.voroby.storebackend.AbstractMvcTest;
import ru.voroby.storebackend.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductControllerTest extends AbstractMvcTest {

  @MockBean
  private ProductController productController;

  private String URI_PATH = "/products";

  private static List<ProductDTO> products;

  @BeforeAll
  public static void init() {
    products = new ArrayList<>();
    products.add(new ProductDTO(1, "name", "sport", "super", 50.0));
    products.add(new ProductDTO(2, "strap", "hiking", "extreme", 20.0));
  }

  @Test
  void getAllProducts() throws Exception {
    when(productController.getAllProducts()).thenReturn(ResponseEntity.ok(products));

    mvc.perform(MockMvcRequestBuilders.get(URI_PATH))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(products), true))
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @WithMockUser(value = "admin", roles = {"ADMIN"})
  void save() throws Exception {
    ProductDTO productDTO = new ProductDTO(null, "water glasses", "swimming", "uniform", 40.0);
    when(productController.save(productDTO)).thenReturn(ResponseEntity.accepted().body(withId(productDTO)));

    MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post(URI_PATH)
      .content(mapper.writeValueAsString(productDTO))
      .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(MockMvcResultMatchers.status().isAccepted())
      .andDo(MockMvcResultHandlers.print())
      .andReturn().getResponse();

    assertEquals(productDTO, mapper.readValue(response.getContentAsString(), ProductDTO.class));
  }

  @Test
  @WithMockUser(value = "admin", roles = {"ADMIN"})
  void delete() throws Exception {
    ProductDTO dto = products.get(0);
    when(productController.delete(dto.getId())).thenReturn(ResponseEntity.ok(dto));

    mvc.perform(MockMvcRequestBuilders.delete(URI_PATH + "/" + dto.getId()))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().
        json(mapper.writeValueAsString(dto), true))
      .andDo(MockMvcResultHandlers.print());
  }

  private ProductDTO withId(ProductDTO dto) {
    dto.setId(100);
    return dto;
  }
}
