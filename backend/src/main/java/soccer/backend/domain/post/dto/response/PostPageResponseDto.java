package soccer.backend.domain.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import soccer.backend.domain.post.entity.Post;
import soccer.backend.global.dto.PageInfo;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostPageResponseDto {

    private List<PostResponseDto> posts;
    private PageInfo pageInfo;

    public static PostPageResponseDto of(Page<Post> posts) {
        return new PostPageResponseDto(
                PostResponseDto.of(posts.getContent()),
                PageInfo.of(posts)
        );
    }
}
