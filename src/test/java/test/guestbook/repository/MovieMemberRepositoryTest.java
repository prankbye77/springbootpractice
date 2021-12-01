package test.guestbook.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.domain.MovieMember;

import java.util.stream.IntStream;

@SpringBootTest
class MovieMemberRepositoryTest {

    @Autowired
    private MovieMemberRepository movieMemberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            MovieMember movieMember = MovieMember.builder()
                    .email("test" + i + "@test.com")
                    .pw("1111")
                    .nickname("reviewer" + i).build();
            movieMemberRepository.save(movieMember);
        });
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteMember() {
        Long mid = 2L;
        MovieMember movieMember = MovieMember.builder().mid(mid).build();
        reviewRepository.deleteByMovieMember(movieMember);
        movieMemberRepository.deleteById(mid);
    }
}