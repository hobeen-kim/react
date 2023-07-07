package soccer.backend.domain.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    private PostUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
