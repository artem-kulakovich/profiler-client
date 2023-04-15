package by.bntu.fitr.profilerclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;



@EnableScheduling
@SpringBootApplication
public class ProfilerClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfilerClientApplication.class, args);
    }

}
