package soccer.backend.domain.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import soccer.backend.utils.InitData;
import soccer.backend.domain.post.dto.response.PostDetailResponseDto;
import soccer.backend.domain.post.entity.Comment;
import soccer.backend.domain.post.entity.Post;
import soccer.backend.domain.post.repository.CommentRepository;
import soccer.backend.domain.post.repository.PostRepository;
import soccer.backend.global.dto.ResponseDto;

import java.util.Map;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@WithMockUser
class PostControllerTest {

    /*@Autowired MockMvc mockMvc;
    @Autowired PostController postController;
    @Autowired InitData initData;
    @Autowired ObjectMapper objectMapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    void beforeEach() {
        initData.initPost();
    }

    @Test
    void getPost() throws Exception{



    }

    @Test
    void getPosts() {
    }

    @Test
    void createPost() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void getComments() {
    }

    @Test
    void createComment() {
    }

    @Test
    void updateComment() {
    }

    @Test
    void deleteComment() {
    }*/
}