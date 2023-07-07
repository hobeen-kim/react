package soccer.backend.domain.post.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import soccer.backend.domain.post.entity.Post;

import java.util.List;
import java.util.Optional;

import static soccer.backend.domain.post.entity.QPost.post;


public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PostRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Post> findByIdWithMember(Long id) {
        return Optional.ofNullable(queryFactory
                .selectFrom(post)
                .join(post.member).fetchJoin()
                .where(post.id.eq(id))
                .fetchOne());
    }

    /**
     * member 와 페치 조인을 하여 post 를 가져온다.
     * @param pageable
     * @return
     */
    @Override
    public Page<Post> findAllByPaging(Pageable pageable) {

        List<Post> results = queryFactory
                .selectFrom(post)
                .innerJoin(post.member).fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 모든 post 개수 == result 의 total count
        JPAQuery<Post> countQuery = queryFactory
                .selectFrom(post);

        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchCount);
    }
}
