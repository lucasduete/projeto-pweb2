package io.github.pw2.horarioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HorarioserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HorarioserviceApplication.class, args);
    }

}
