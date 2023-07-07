package soccer.backend.domain.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soccer.backend.auth.entity.Member;
import soccer.backend.global.entity.BaseEntity;
import soccer.backend.global.exception.BusinessException;
import soccer.backend.global.exception.ExceptionCode;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    //member 에서 comment 에 접근할 일이 없어서 단방향으로 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    /*
    * 테스트 빌더용
     */
    @Builder
    private Comment(Long id, String content, Member member, Post post, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.content = content;
        this.member = member;
        this.post = post;
        super.createdDate = createdDate;
        super.modifiedDate = modifiedDate;
    }

    private Comment(String content) {
        this.content = content;
    }

    /**
     * Member 는 Comment 에 접근할 일이 없어서 단방향으로 설정
     * @param member : 댓글을 작성한 멤버
     */
    private void addMember(Member member) {
        this.member = member;
    }

    /**
     * Post 는 Comment 에 접근하기 때문에 양방향으로 설정
     * @param post : 댓글이 달린 게시글
     */
    private void addPost(Post post) {
        this.post = post;
        post.addComment(this);
    }

    /**
     * 댓글 생성 메서드
     * @param member : 댓글을 작성한 멤버
     * @param post : 댓글이 달린 게시글
     * @param content : 댓글 내용
     * @return : 생성된 댓글
     */
    public static Comment createComment(Member member, Post post, String content) {
        Comment comment = new Comment(content);
        comment.addMember(member);
        comment.addPost(post);

        return comment;
    }

    /**
     * 댓글 수정 메서드
     * @param member : 댓글을 수정하려는 멤버
     * @param content : 수정할 댓글 내용
     * @return : 수정된 댓글의 id
     */
    public Long updateComment(Member member, String content) {
        validateMember(member);
        this.content = content;

        return this.id;
    }

    /**
     * 댓글을 작성한 멤버와 수정하려는 멤버가 같은지 확인
     * @param member : 댓글을 수정하려는 멤버
     */
    private void validateMember(Member member) {
        if(this.member != member){
            throw new BusinessException(ExceptionCode.ACCESS_DENIED);
        }
    }
}
