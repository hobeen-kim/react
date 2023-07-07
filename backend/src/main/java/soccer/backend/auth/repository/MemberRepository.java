package soccer.backend.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.backend.auth.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByMemberId(String memberId);

    boolean existsByMemberId(String memberId);
}