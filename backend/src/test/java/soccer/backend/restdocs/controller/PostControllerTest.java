package soccer.backend.restdocs.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.restdocs.snippet.Attributes;
import soccer.backend.auth.entity.Member;
import soccer.backend.domain.post.dto.request.CommentCreateRequestDto;
import soccer.backend.domain.post.dto.request.CommentUpdateRequestDto;
import soccer.backend.domain.post.dto.request.PostCreateRequestDto;
import soccer.backend.domain.post.dto.request.PostUpdateRequestDto;
import soccer.backend.domain.post.dto.response.CommentPageResponseDto;
import soccer.backend.domain.post.dto.response.PostPageResponseDto;
import soccer.backend.domain.post.dto.response.PostResponseDto;
import soccer.backend.domain.post.dto.servicedto.PostServiceDto;
import soccer.backend.domain.post.entity.Comment;
import soccer.backend.domain.post.entity.Post;
import soccer.backend.global.dto.ResponseDto;
import soccer.backend.restdocs.utils.config.RestDocsTestSupport;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostControllerTest extends RestDocsTestSupport {

    @Test
    @DisplayName("게시물 작성")
    void createPost() throws Exception {
        //given
        Long postId = 1L;

        PostCreateRequestDto request = PostCreateRequestDto.builder()
                .title("title")
                .content("content")
                .build();

        given(postService.createPost(any())).willReturn(postId);

        //when
        mockMvc.perform(
                    post("/posts")
                    .contentType(APPLICATION_JSON)
                    .header("Authorization", "Bearer DFN48ASDFNO14P9FANKL1")
                    .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andDo(documentHandler.document(
                        requestHeaders(
                                headerWithName("Authorization").description("Access Token").attributes(new Attributes.Attribute("format", "Bearer DFN48ASDFNO14P9FANKL1"))
                        ),
                        requestFields(
                                fieldWithPath("title").type(STRING).description("게시물 제목"),
                                fieldWithPath("content").type(STRING).description("게시물 내용")
                        ),
                        responseHeaders(
                                headerWithName("Location").description("생성된 게시물 URI")
                        )
                ));
    }

    @Test
    @DisplayName("게시물 1건 조회")
    void getPost() throws Exception {
        //given
        Post post = createPost(1L);
        List<Comment> comments = createComments(post, 10);

        given(postService.getPost(anyLong())).willReturn(post);
        given(commentService.getComments(anyLong(), any(Pageable.class)))
                .willReturn(new PageImpl<>(comments, PageRequest.of(0, 10), 100));

        //when
        mockMvc.perform(
                    get("/posts/{postId}", post.getId())
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(documentHandler.document(
                    pathParameters(
                            parameterWithName("postId").description("게시물 ID")
                    ),
                    responseFields(
                            fieldWithPath("status").type(STRING).description("상태"),
                            fieldWithPath("code").type(NUMBER).description("코드"),
                            fieldWithPath("message").type(STRING).description("메세지"),
                            fieldWithPath("data").type(OBJECT).description("응답 데이터"),
                            fieldWithPath("data.postId").type(NUMBER).description("포스트 ID"),
                            fieldWithPath("data.title").type(STRING).description("포스트 제목"),
                            fieldWithPath("data.content").type(STRING).description("포스트 내용"),
                            fieldWithPath("data.memberId").type(STRING).description("포스트 작성자"),
                            fieldWithPath("data.createdDate").type(STRING).description("포스트 생성 일자"),
                            fieldWithPath("data.modifiedDate").type(STRING).description("포스트 수정 일자"),
                            fieldWithPath("data.comments").type(OBJECT).description("댓글 10개"),
                            fieldWithPath("data.comments.comments").type(ARRAY).description("댓글 10개 데이터"),
                            fieldWithPath("data.comments.comments[].commentId").type(NUMBER).description("댓글 ID"),
                            fieldWithPath("data.comments.comments[].content").type(STRING).description("댓글 내용"),
                            fieldWithPath("data.comments.comments[].memberId").type(STRING).description("댓글 작성자"),
                            fieldWithPath("data.comments.comments[].createdDate").type(STRING).description("댓글 생성 일자"),
                            fieldWithPath("data.comments.comments[].modifiedDate").type(STRING).description("댓글 수정 일자"),
                            fieldWithPath("data.comments.pageInfo").type(OBJECT).description("댓글 페이징 정보"),
                            fieldWithPath("data.comments.pageInfo.page").type(NUMBER).description("댓글 현재 페이지"),
                            fieldWithPath("data.comments.pageInfo.size").type(NUMBER).description("댓글 현재 개수"),
                            fieldWithPath("data.comments.pageInfo.totalPage").type(NUMBER).description("댓글 총 페이지"),
                            fieldWithPath("data.comments.pageInfo.totalSize").type(NUMBER).description("댓글 총 개수"),
                            fieldWithPath("data.comments.pageInfo.first").type(BOOLEAN).description("첫 페이지인지"),
                            fieldWithPath("data.comments.pageInfo.last").type(BOOLEAN).description("마지막 페이지인지"),
                            fieldWithPath("data.comments.pageInfo.hasNext").type(BOOLEAN).description("다음 페이지 여부"),
                            fieldWithPath("data.comments.pageInfo.hasPrevious").type(BOOLEAN).description("이전 페이지 여부")
                    )
                ));
    }

    @Test
    @DisplayName("게시물 여러 건 조회")
    void getPosts() throws Exception {
        //given
        List<Post> posts = createPosts(10);
        Page<Post> pagedPosts = new PageImpl<>(posts, PageRequest.of(0, 10), 100);

        given(postService.getPosts(any(Pageable.class))).willReturn(pagedPosts);

        String content = objectMapper.writeValueAsString(ResponseDto.ok(PostPageResponseDto.of(pagedPosts)));

        //when
        mockMvc.perform(
                        get("/posts")
                                .param("page", "0")
                                .param("size", "10")
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(content))
                .andDo(documentHandler.document(
                        queryParameters(
                                parameterWithName("page").description("요청 페이지 번호"),
                                parameterWithName("size").description("요청 페이지 크기")
                        ),
                        responseFields(
                                fieldWithPath("status").type(STRING).description("상태"),
                                fieldWithPath("code").type(NUMBER).description("코드"),
                                fieldWithPath("message").type(STRING).description("메세지"),
                                fieldWithPath("data").type(OBJECT).description("응답 데이터"),
                                fieldWithPath("data.posts").type(ARRAY).description("포스트 데이터"),
                                fieldWithPath("data.posts[].postId").type(NUMBER).description("포스트 ID"),
                                fieldWithPath("data.posts[].title").type(STRING).description("포스트 제목"),
                                fieldWithPath("data.posts[].content").type(STRING).description("포스트 내용"),
                                fieldWithPath("data.posts[].memberId").type(STRING).description("포스트 작성자"),
                                fieldWithPath("data.posts[].createdDate").type(STRING).description("포스트 생성 일자"),
                                fieldWithPath("data.posts[].modifiedDate").type(STRING).description("포스트 수정 일자"),
                                fieldWithPath("data.pageInfo").type(OBJECT).description("포스트 페이징 정보"),
                                fieldWithPath("data.pageInfo.page").type(NUMBER).description("포스트 현재 페이지"),
                                fieldWithPath("data.pageInfo.size").type(NUMBER).description("포스트 현재 개수"),
                                fieldWithPath("data.pageInfo.totalPage").type(NUMBER).description("포스트 총 페이지"),
                                fieldWithPath("data.pageInfo.totalSize").type(NUMBER).description("포스트 총 개수"),
                                fieldWithPath("data.pageInfo.first").type(BOOLEAN).description("첫 페이지인지"),
                                fieldWithPath("data.pageInfo.last").type(BOOLEAN).description("마지막 페이지인지"),
                                fieldWithPath("data.pageInfo.hasNext").type(BOOLEAN).description("다음 페이지 여부"),
                                fieldWithPath("data.pageInfo.hasPrevious").type(BOOLEAN).description("이전 페이지 여부")
                        )
                ));
    }

    @Test
    @DisplayName("게시물 수정")
    void updatePost() throws Exception {
        //given
        Post post = createPost(1L, "updated title", "updated content");
        PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
                .title("updated title")
                .content("updated content")
                .build();

        given(postService.updatePost(anyLong(), any(PostServiceDto.class))).willReturn(post);

        String content = objectMapper.writeValueAsString(ResponseDto.ok(PostResponseDto.of(post)));

        //when
        mockMvc.perform(
                        patch("/posts/{postId}", post.getId())
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON)
                                .header("Authorization", "Bearer AccessTokenExample")
                                .content(objectMapper.writeValueAsString(requestDto))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Location", is(endsWith("/posts/" + post.getId()))))
                .andExpect(content().json(content))
                .andDo(documentHandler.document(
                        requestHeaders(
                                headerWithName("Authorization").description("Access Token").attributes(new Attributes.Attribute("format", "Bearer DFN48ASDFNO14P9FANKL1"))
                        ),
                        pathParameters(
                                parameterWithName("postId").description("수정할 포스트 ID")
                        ),
                        requestFields(
                                fieldWithPath("title").type(STRING).description("수정할 포스트 제목"),
                                fieldWithPath("content").type(STRING).description("수정할 포스트 내용")
                        ),
                        responseFields(
                                fieldWithPath("status").type(STRING).description("상태"),
                                fieldWithPath("code").type(NUMBER).description("코드"),
                                fieldWithPath("message").type(STRING).description("메세지"),
                                fieldWithPath("data").type(OBJECT).description("응답 데이터"),
                                fieldWithPath("data.postId").type(NUMBER).description("포스트 ID"),
                                fieldWithPath("data.title").type(STRING).description("수정된 포스트 제목"),
                                fieldWithPath("data.content").type(STRING).description("수정된 포스트 내용"),
                                fieldWithPath("data.memberId").type(STRING).description("포스트 작성자"),
                                fieldWithPath("data.createdDate").type(STRING).description("포스트 생성 일자"),
                                fieldWithPath("data.modifiedDate").type(STRING).description("포스트 수정 일자")
                        )
                ));
    }

    @Test
    @DisplayName("게시물 삭제")
    void deletePost() throws Exception {
        //given
        Long postId = 1L;

        //when
        mockMvc.perform(
                        delete("/posts/{postId}", postId)
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON)
                                .header("Authorization", "Bearer AccessTokenExample")
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(documentHandler.document(
                        requestHeaders(
                                headerWithName("Authorization").description("Access Token").attributes(new Attributes.Attribute("format", "Bearer DFN48ASDFNO14P9FANKL1"))
                        ),
                        pathParameters(
                                parameterWithName("postId").description("삭제할 포스트 ID")
                        )
                ));
    }

    @Test
    @DisplayName("댓글 생성")
    void createComment() throws Exception {
        //given
        Long postId = 1L;
        CommentCreateRequestDto request = CommentCreateRequestDto.builder()
                .content("content")
                .build();

        //when //then
        mockMvc.perform(
                        post("/posts/{postId}/comments", postId)
                                .contentType(APPLICATION_JSON)
                                .header("Authorization", "Bearer AccessTokenExample")
                                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(endsWith("/posts/" + postId))))
                .andDo(documentHandler.document(
                        requestHeaders(
                                headerWithName("Authorization").description("Access Token").attributes(new Attributes.Attribute("format", "Bearer DFN48ASDFNO14P9FANKL1"))
                        ),
                        pathParameters(
                                parameterWithName("postId").description("댓글을 생성할 포스트 ID")
                        ),
                        responseHeaders(
                                headerWithName("Location").description("생성된 댓글이 있는 POST 의 URI")
                        ),
                        requestFields(
                                fieldWithPath("content").type(STRING).description("댓글 내용")
                        )
                ));
    }

    @Test
    @DisplayName("댓글 여러 건 조회")
    void getComments() throws Exception {
        //given
        Post post = createPost(1L);
        List<Comment> comments = createComments(post, 10);
        int page = 0;
        int size = 10;
        int totalSize = 100;
        Page<Comment> pagedComments = new PageImpl<>(comments, PageRequest.of(page, size), totalSize);

        given(commentService.getComments(anyLong(), any(Pageable.class))).willReturn(pagedComments);

        String content = objectMapper.writeValueAsString(ResponseDto.ok(CommentPageResponseDto.of(pagedComments)));

        //when
        mockMvc.perform(
                        get("/posts/{postId}/comments", post.getId())
                                .param("page", "0")
                                .param("size", "10")
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(content))
                .andDo(documentHandler.document(
                        pathParameters(
                                parameterWithName("postId").description("댓글을 조회할 포스트 ID")
                        ),
                        queryParameters(
                                parameterWithName("page").description("요청 페이지 번호"),
                                parameterWithName("size").description("요청 페이지 크기")
                        ),
                        responseFields(
                                fieldWithPath("status").type(STRING).description("상태"),
                                fieldWithPath("code").type(NUMBER).description("코드"),
                                fieldWithPath("message").type(STRING).description("메세지"),
                                fieldWithPath("data").type(OBJECT).description("응답 데이터"),
                                fieldWithPath("data.comments").type(ARRAY).description("댓글 데이터"),
                                fieldWithPath("data.comments[].commentId").type(NUMBER).description("댓글 ID"),
                                fieldWithPath("data.comments[].content").type(STRING).description("댓글 내용"),
                                fieldWithPath("data.comments[].memberId").type(STRING).description("댓글 작성자"),
                                fieldWithPath("data.comments[].createdDate").type(STRING).description("댓글 생성 일자"),
                                fieldWithPath("data.comments[].modifiedDate").type(STRING).description("댓글 수정 일자"),
                                fieldWithPath("data.pageInfo").type(OBJECT).description("댓글 페이징 정보"),
                                fieldWithPath("data.pageInfo.page").type(NUMBER).description("댓글 현재 페이지"),
                                fieldWithPath("data.pageInfo.size").type(NUMBER).description("댓글 현재 개수"),
                                fieldWithPath("data.pageInfo.totalPage").type(NUMBER).description("댓글 총 페이지"),
                                fieldWithPath("data.pageInfo.totalSize").type(NUMBER).description("댓글 총 개수"),
                                fieldWithPath("data.pageInfo.first").type(BOOLEAN).description("첫 페이지인지"),
                                fieldWithPath("data.pageInfo.last").type(BOOLEAN).description("마지막 페이지인지"),
                                fieldWithPath("data.pageInfo.hasNext").type(BOOLEAN).description("다음 페이지 여부"),
                                fieldWithPath("data.pageInfo.hasPrevious").type(BOOLEAN).description("이전 페이지 여부")
                        )
                ));
    }

    @Test
    @DisplayName("댓글 수정")
    void updateComment() throws Exception {
        //given
        Long postId = 1L;
        Long commentId = 1L;

        CommentUpdateRequestDto request = CommentUpdateRequestDto.builder()
                .content("updated comment content")
                .build();

        //when //then
        mockMvc.perform(
                        patch("/posts/{postId}/comments/{commentId}", postId, commentId)
                                .contentType(APPLICATION_JSON)
                                .header("Authorization", "Bearer AccessTokenExample")
                                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(documentHandler.document(
                        requestHeaders(
                                headerWithName("Authorization").description("Access Token").attributes(new Attributes.Attribute("format", "Bearer DFN48ASDFNO14P9FANKL1"))
                        ),
                        pathParameters(
                                parameterWithName("postId").description("댓글을 수정할 포스트 ID"),
                                parameterWithName("commentId").description("수정할 댓글 ID")
                        ),
                        requestFields(
                                fieldWithPath("content").type(STRING).description("댓글 내용")
                        )
                ));
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteComment() throws Exception {
        //given
        Long postId = 1L;
        Long commentId = 1L;

        //when
        mockMvc.perform(
                        delete("/posts/{postId}/comments/{commentId}", postId, commentId)
                                .contentType(APPLICATION_JSON)
                                .accept(APPLICATION_JSON)
                                .header("Authorization", "Bearer AccessTokenExample")
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(documentHandler.document(
                        requestHeaders(
                                headerWithName("Authorization").description("Access Token").attributes(new Attributes.Attribute("format", "Bearer DFN48ASDFNO14P9FANKL1"))
                        ),
                        pathParameters(
                                parameterWithName("postId").description("대상 포스트 ID"),
                                parameterWithName("commentId").description("삭제할 댓글 ID")
                        )
                ));
    }

    private Post createPost(Long id) {
        return Post.builder()
                .id(id)
                .title("title")
                .content("content")
                .member(createMember(id))
                .createdDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0))
                .modifiedDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0))
                .build();
    }

    private Post createPost(Long id, String title, String content) {
        return Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .member(createMember(id))
                .createdDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0))
                .modifiedDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0))
                .build();
    }



    private List<Post> createPosts(int count){
        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            posts.add(createPost((long) i));
        }

        return posts;
    }

    private Comment createComment(Long id, Post post){
        return Comment.builder()
                .id(id)
                .content("content")
                .member(createMember(id))
                .post(post)
                .createdDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0))
                .modifiedDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0))
                .build();
    }

    private List<Comment> createComments(Post post, int count){
        List<Comment> comments = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            comments.add(createComment((long) i, post));
        }

        return comments;
    }

    private Member createMember(Long id){
        return Member.builder()
                .id(id)
                .memberId("memberId")
                .build();
    }
}
