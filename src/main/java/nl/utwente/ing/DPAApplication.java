package nl.utwente.ing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class DPAApplication {
    public static void main(String[] args) {
        SpringApplication.run(DPAApplication.class, args);
    }
}