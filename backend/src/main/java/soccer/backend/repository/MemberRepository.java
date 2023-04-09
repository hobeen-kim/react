package soccer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.backend.domain.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByMemberId(String memberId);

    boolean existsByMemberId(String memberId);
}