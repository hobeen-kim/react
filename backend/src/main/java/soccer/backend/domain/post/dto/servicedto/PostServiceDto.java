package soccer.backend.domain.post.dto.servicedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soccer.backend.domain.post.dto.request.PostCreateRequestDto;
import soccer.backend.domain.post.dto.request.PostUpdateRequestDto;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class PostServiceDto {


    private String title;
    private String content;

    public static PostServiceDto of(PostCreateRequestDto postCreateRequestDto) {
        return new PostServiceDto(postCreateRequestDto.getTitle(), postCreateRequestDto.getContent());
    }

    public static PostServiceDto of(PostUpdateRequestDto postUpdateRequestDto) {
        return new PostServiceDto(postUpdateRequestDto.getTitle(), postUpdateRequestDto.getContent());
    }
}
