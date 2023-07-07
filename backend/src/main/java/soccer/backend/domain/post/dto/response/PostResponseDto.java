package soccer.backend.domain.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soccer.backend.domain.post.entity.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {

    private Long postId;
    private String title;
    private String content;
    private String memberId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static PostResponseDto of(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getMember().getMemberId(),
                post.getCreatedDate(),
                post.getModifiedDate());
    }

    public static List<PostResponseDto> of(List<Post> posts) {
        return posts.stream().map(PostResponseDto::of).collect(Collectors.toList());
    }



}
