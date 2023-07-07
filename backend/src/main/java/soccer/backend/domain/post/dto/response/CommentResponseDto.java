package soccer.backend.domain.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soccer.backend.domain.post.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

    private Long commentId;
    private String content;
    private String memberId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    /**
     * List<Comment> 를 받아서 List<CommentResponseDto> 로 변경
     * @param comments : List<Comment>
     * @return : List<CommentResponseDto>
     */
    public static List<CommentResponseDto> of(List<Comment> comments) {

        return comments.stream().map(CommentResponseDto::of).collect(Collectors.toList());
    }

    public static CommentResponseDto of(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getMember().getMemberId(),
                comment.getCreatedDate(),
                comment.getModifiedDate());
    }


}
