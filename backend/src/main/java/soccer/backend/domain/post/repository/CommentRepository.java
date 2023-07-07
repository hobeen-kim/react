package soccer.backend.domain.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import soccer.backend.domain.post.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom{

    @Query("delete from Comment c where c.id = :id and c.member.id = :currentMemberId")
    @Modifying
    Long deleteByIdWithMemberId(Long id, Long currentMemberId);
}