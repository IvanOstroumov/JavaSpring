package ch.samt.customertrue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CustomerTrueApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerTrueApplication.class, args);
    }

}
