package soccer.backend.domain.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import soccer.backend.domain.post.entity.Post;

import java.util.Optional;

public interface PostRepositoryCustom {

    Optional<Post> findByIdWithMember(Long id);
    Page<Post> findAllByPaging(Pageable pageable);
}
