package soccer.backend.domain.post.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import soccer.backend.domain.post.dto.request.CommentCreateRequestDto;
import soccer.backend.domain.post.dto.request.CommentUpdateRequestDto;
import soccer.backend.domain.post.dto.request.PostCreateRequestDto;
import soccer.backend.domain.post.dto.request.PostUpdateRequestDto;
import soccer.backend.domain.post.dto.response.CommentPageResponseDto;
import soccer.backend.domain.post.dto.response.PostDetailResponseDto;
import soccer.backend.domain.post.dto.response.PostPageResponseDto;
import soccer.backend.domain.post.dto.response.PostResponseDto;
import soccer.backend.domain.post.dto.servicedto.CommentServiceDto;
import soccer.backend.domain.post.dto.servicedto.PostServiceDto;
import soccer.backend.domain.post.entity.Comment;
import soccer.backend.domain.post.entity.Post;
import soccer.backend.domain.post.service.CommentService;
import soccer.backend.domain.post.service.PostService;
import soccer.backend.global.dto.ResponseDto;

import java.net.URI;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Validated
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/{postId}")
    public ResponseEntity<ResponseDto<PostDetailResponseDto>> getPost(@PathVariable @Positive Long postId){

        Post post = postService.getPost(postId);
        Page<Comment> comments = commentService.getComments(postId, PageRequest.of(0, 10));

        return ResponseEntity.ok().body(ResponseDto.ok(PostDetailResponseDto.of(post, comments)));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<PostPageResponseDto>> getPosts(Pageable pageable){

        Page<Post> posts = postService.getPosts(pageable);

        return ResponseEntity.ok().body(ResponseDto.ok(PostPageResponseDto.of(posts)));

    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostCreateRequestDto postCreateRequestDto){

        Long postId = postService.createPost(PostServiceDto.of(postCreateRequestDto));

        //uri 로 location 정보 주기
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{postId}")
                .buildAndExpand(postId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PatchMapping("/{postId}")
    public ResponseEntity updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto postUpdateRequestDto){

        Post updatePost = postService.updatePost(postId, PostServiceDto.of(postUpdateRequestDto));

        String uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .build().toUri().toString();

        //헤더 Location 에 uri 정보를 담아서 보내주기
        HttpHeaders headers = new HttpHeaders();
        headers.set("Location", uri);

        return new ResponseEntity<>(ResponseDto.ok(PostResponseDto.of(updatePost)), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId){

        postService.delete(postId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity getComments(@PathVariable Long postId, Pageable pageable){

        Page<Comment> comments = commentService.getComments(postId, pageable);

        return ResponseEntity.ok().body(ResponseDto.ok(CommentPageResponseDto.of(comments)));
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity createComment(@PathVariable Long postId, @RequestBody CommentCreateRequestDto commentCreateRequestDto){

        commentService.createComment(postId, CommentServiceDto.of(commentCreateRequestDto));

        //해당 post 로 location 정보 주기
        URI uri = URI.create("/posts/" + postId);

        //프론트가 201 응답을 받으면 받은 정보를 그대로 display 하면 되니까 따로 정보를 보내지 않습니다.
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping("/{postId}/comments/{commentId}") // postId 는 필요 없습니다.
    public ResponseEntity updateComment(@PathVariable Long postId,
                                        @PathVariable Long commentId,
                                        @RequestBody CommentUpdateRequestDto commentUpdateRequestDto){

        commentService.updateComment(commentId, CommentServiceDto.of(commentUpdateRequestDto));

        //프론트가 204 응답을 받으면 받은 정보를 그대로 display 하면 되니까 따로 정보를 보내지 않습니다.
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long commentId){

        commentService.deleteComment(commentId);

        //프론트가 204 응답을 받으면 받은 정보를 그대로 display 하면 되니까 따로 정보를 보내지 않습니다.
        return ResponseEntity.noContent().build();
    }


}
