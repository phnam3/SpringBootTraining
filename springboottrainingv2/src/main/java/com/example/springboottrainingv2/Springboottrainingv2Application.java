package com.example.springboottrainingv2;

import com.example.springboottrainingv2.entity.config.Profile1;
import com.example.springboottrainingv2.entity.config.Profile2;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@RequiredArgsConstructor
public class Springboottrainingv2Application {

//    private final Profile1 profile1;
    private final Profile2 profile2;
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(Springboottrainingv2Application.class, args);
    }

    public static void restart(){
        Thread thread = new Thread(() -> {
            context.close();
            context = SpringApplication.run(Springboottrainingv2Application.class, "--spring.profiles.active=profile1");
        });

        thread.setDaemon(false);
        thread.start();
    }

}
