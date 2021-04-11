package ru.voroby.storebackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.voroby.storebackend.repository.OrderDAO;
import ru.voroby.storebackend.repository.ProductDAO;

@WebMvcTest
public class AbstractMvcTest {

  @Autowired
  protected MockMvc mvc;

  @Autowired
  protected ObjectMapper mapper;

  @MockBean
  protected OrderDAO orderDAO;

  @MockBean
  protected ProductDAO productDAO;

  @MockBean
  protected JPAStreamer jpaStreamer;

}
