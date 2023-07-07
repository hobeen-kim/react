package soccer.backend.domain.post.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import soccer.backend.domain.post.entity.Comment;
import soccer.backend.domain.post.entity.Post;

import java.util.List;
import java.util.Optional;

import static soccer.backend.domain.post.entity.QComment.comment;


public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<Comment> findAllByPaging(Long postId, Pageable pageable) {
        List<Comment> results = queryFactory
                .selectFrom(comment)
                .join(comment.member).fetchJoin()
                .where(comment.post.id.eq(postId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Comment> countQuery = queryFactory
                .selectFrom(comment)
                .where(comment.post.id.eq(postId));

        return PageableExecutionUtils.getPage(results, pageable, countQuery::fetchCount);

    }

    @Override
    public Optional<Comment> findByIdWithMember(Long id) {

        return Optional.ofNullable(
                queryFactory
                    .selectFrom(comment)
                    .join(comment.member).fetchJoin()
                    .where(comment.id.eq(id))
                    .fetchOne());
    }


}
