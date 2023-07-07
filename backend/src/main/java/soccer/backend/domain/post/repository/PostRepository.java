package soccer.backend.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import soccer.backend.domain.post.entity.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    @Query("delete from Post p where p.id = :id and p.member.id = :currentMemberId")
    @Modifying
    Long deleteByIdWithMemberId(Long id, Long currentMemberId);
}