package io.github.pw2.professorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProfessorserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfessorserviceApplication.class, args);
    }

}
