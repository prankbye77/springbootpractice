package test.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.guestbook.domain.MovieMember;

public interface MovieMemberRepository extends JpaRepository<MovieMember, Long> {
}
