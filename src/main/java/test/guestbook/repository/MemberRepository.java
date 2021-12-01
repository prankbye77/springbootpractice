package test.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.guestbook.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}
