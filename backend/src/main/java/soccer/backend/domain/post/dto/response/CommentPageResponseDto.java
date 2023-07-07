package soccer.backend.domain.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import soccer.backend.domain.post.entity.Comment;
import soccer.backend.global.dto.PageInfo;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPageResponseDto {

    List<CommentResponseDto> comments;
    PageInfo pageInfo;

    public static CommentPageResponseDto of(Page<Comment> comments) {
        return new CommentPageResponseDto(
                CommentResponseDto.of(comments.getContent()),
                PageInfo.of(comments)
        );
    }
}
