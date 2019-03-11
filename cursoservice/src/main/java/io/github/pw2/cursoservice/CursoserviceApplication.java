package io.github.pw2.cursoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CursoserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CursoserviceApplication.class, args);
    }

}
