package test.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.guestbook.domain.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
