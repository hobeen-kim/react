package soccer.backend.domain.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import soccer.backend.domain.post.entity.Comment;
import soccer.backend.domain.post.entity.Post;

import java.util.Optional;

public interface CommentRepositoryCustom {

    Page<Comment> findAllByPaging(Long postId, Pageable pageable);

    Optional<Comment> findByIdWithMember(Long id);

}
