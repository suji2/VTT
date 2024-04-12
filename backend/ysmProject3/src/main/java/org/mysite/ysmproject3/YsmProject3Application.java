package org.mysite.ysmproject3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class YsmProject3Application {

    public static void main(String[] args) {
        SpringApplication.run(YsmProject3Application.class, args);
    }

}
