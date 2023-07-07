package soccer.backend.domain.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateRequestDto {

    private String content;

    @Builder
    private CommentUpdateRequestDto(String content) {
        this.content = content;
    }
}
