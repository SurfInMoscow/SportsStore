package ru.voroby.storebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("ru.voroby.storebackend.model")
@EnableJpaRepositories(basePackages = {"ru.voroby.storebackend.repository"})
public class StoreBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(StoreBackendApplication.class, args);
  }

}
