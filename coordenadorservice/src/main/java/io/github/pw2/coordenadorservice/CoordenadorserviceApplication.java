package io.github.pw2.coordenadorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CoordenadorserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoordenadorserviceApplication.class, args);
    }

}
