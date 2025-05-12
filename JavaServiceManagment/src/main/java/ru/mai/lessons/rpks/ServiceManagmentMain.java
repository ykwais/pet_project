package ru.mai.lessons.rpks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.mai.lessons.rpks.repository")
@EntityScan("ru.mai.lessons.rpks.model")
@Slf4j
public class ServiceManagmentMain {
    public static void main(String[] args) {
        log.info("made by ykwais");
        SpringApplication.run(ServiceManagmentMain.class, args);
    }
}

//http://localhost:8080/swagger-ui/index.html
//http://localhost:8080/actuator