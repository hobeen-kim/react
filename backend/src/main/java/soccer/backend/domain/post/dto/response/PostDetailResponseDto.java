package soccer.backend.domain.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import soccer.backend.domain.post.entity.Comment;
import soccer.backend.domain.post.entity.Post;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostDetailResponseDto {

    private Long postId;
    private String title;
    private String content;
    private String memberId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private CommentPageResponseDto comments;

    public static PostDetailResponseDto of(Post post, Page<Comment> comments) {
        return new PostDetailResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getMember().getMemberId(),
                post.getCreatedDate(),
                post.getModifiedDate(),
                CommentPageResponseDto.of(comments));
    }

}
