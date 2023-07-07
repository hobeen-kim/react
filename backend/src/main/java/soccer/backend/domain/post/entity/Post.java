package soccer.backend.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;
import soccer.backend.auth.entity.Member;
import soccer.backend.global.entity.BaseEntity;
import soccer.backend.global.exception.BusinessException;
import soccer.backend.global.exception.ExceptionCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "title", "content"})
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Post(String title, String content){
        this.title = title;
        this.content = content;
    }

    private void addMember(Member member){
        this.member = member;
        member.addPost(this);
    }

    /**
     * 테스트 빌더용
     */
    @Builder
    private Post(Long id, String title, String content, Member member, List<Comment> comments, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
        this.comments = comments;
        super.createdDate = createdDate;
        super.modifiedDate = modifiedDate;
    }

    // post 가 삭제되면 comment 의 생명주기도 같이하기 때문에 cascade = CascadeType.ALL
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addComments(List<Comment> comments) {
        this.comments.addAll(comments);
    }

    public static Post createPost(Member member, String title, String content) {
        Post post = new Post(title, content);
        post.addMember(member);
        return post;
    }

    public Post updatePost(Member member, String title, String content) {
        validateMember(member);
        this.title = title;
        this.content = content;

        return this;
    }

    private void validateMember(Member member) {
        if(this.member != member){
            throw new BusinessException(ExceptionCode.ACCESS_DENIED);
        }
    }
}
