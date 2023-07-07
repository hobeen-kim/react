package soccer.backend.domain.post.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreateRequestDto {

    private String title;
    private String content;

    @Builder
    private PostCreateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
