package test.guestbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import test.guestbook.domain.Guestbook;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableJpaAuditing
public class GuestbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuestbookApplication.class, args);
    }
}
