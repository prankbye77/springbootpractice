package test.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import test.guestbook.domain.Guestbook;
import test.guestbook.domain.QGuestbook;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository guestbookRepository;

    public void setup() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Title" + i)
                    .content("Content" + i)
                    .writer("user" + (i % 10))
                    .build();
            guestbookRepository.save(guestbook);
        });
    }

    @Test
    public void insertDummies() {
        setup();
    }

    @Test
    public void updateTest() {
        setup();
        Optional<Guestbook> result = guestbookRepository.findById(100L);
        if (result.isPresent()) {
            Guestbook guestbook = result.get();
            guestbook.setTitle("changed title");
            guestbook.setContent("changed content");

            guestbookRepository.save(guestbook);
        }
    }

    @Test
    public void testQuery1() {
        setup();
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qGuestbook.title.contains(keyword);

        builder.and(expression);

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
        result.stream().forEach(guestbook -> {
            System.out.println("guestbook = " + guestbook);
        });
    }
}