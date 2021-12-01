package test.guestbook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import test.guestbook.domain.Guestbook;
import test.guestbook.repository.GuestbookRepository;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

//@Component
@RequiredArgsConstructor
public class InitData {

    private final GuestbookRepository guestbookRepository;

    @PostConstruct
    public void init() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Title" + i)
                    .content("Content" + i)
                    .writer("User" + (i % 10))
                    .build();
            System.out.println("guestbook = " + guestbook);
            guestbookRepository.save(guestbook);
        });
    }
}
