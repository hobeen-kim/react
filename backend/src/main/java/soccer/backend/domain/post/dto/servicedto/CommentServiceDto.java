package soccer.backend.domain.post.dto.servicedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soccer.backend.domain.post.dto.request.CommentCreateRequestDto;
import soccer.backend.domain.post.dto.request.CommentUpdateRequestDto;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentServiceDto {

    private String content;

    public static CommentServiceDto of(CommentCreateRequestDto requestDto) {
        return new CommentServiceDto(requestDto.getContent());
    }

    public static CommentServiceDto of(CommentUpdateRequestDto requestDto) {
        return new CommentServiceDto(requestDto.getContent());
    }

}
