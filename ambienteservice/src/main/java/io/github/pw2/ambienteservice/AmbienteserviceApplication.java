package io.github.pw2.ambienteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AmbienteserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmbienteserviceApplication.class, args);
    }

}
