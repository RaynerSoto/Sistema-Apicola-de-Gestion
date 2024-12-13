package cu.edu.cujae.gestion.postgresql.microservicepais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class PaisApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaisApplication.class, args);
    }

}
