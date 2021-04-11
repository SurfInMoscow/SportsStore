package ru.voroby.storebackend;

import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
@EntityScan("ru.voroby.storebackend.model")
@EnableJpaRepositories({"ru.voroby.storebackend.repository"})
public class StoreBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(StoreBackendApplication.class, args);
  }

  @Bean
  JPAStreamer jpaStreamer(EntityManagerFactory entityManagerFactory) {
    return JPAStreamer.of(entityManagerFactory);
  }

}
