package soccer.backend.restdocs.utils.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import soccer.backend.domain.post.controller.PostController;
import soccer.backend.domain.post.service.CommentService;
import soccer.backend.domain.post.service.PostService;
import soccer.backend.restdocs.utils.CommonDocController;

@Import(TestExceptionHandler.class)
@WebMvcTest({PostController.class, CommonDocController.class})
@MockBean(JpaMetamodelMappingContext.class)
public abstract class AnnotationSupport {

    @MockBean protected PostService postService;
    @MockBean protected CommentService commentService;

    @Autowired protected ObjectMapper objectMapper;
    @Autowired protected MockMvc mockMvc;
}
