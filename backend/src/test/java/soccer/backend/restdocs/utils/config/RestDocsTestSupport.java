package soccer.backend.restdocs.utils.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@ExtendWith({RestDocumentationExtension.class})
public abstract class RestDocsTestSupport extends AnnotationSupport {

    protected RestDocumentationResultHandler documentHandler;

    @BeforeEach
    void setUp(WebApplicationContext context,
               final RestDocumentationContextProvider restDocumentation,
               TestInfo testInfo) {


        String className = testInfo.getTestClass().orElseThrow().getSimpleName()
                .replace("ControllerTest", "").toLowerCase();
        String methodName = testInfo.getTestMethod().orElseThrow().getName().toLowerCase();

        documentHandler = document(
                className + "/" + methodName,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())
        );

        this.mockMvc = webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(documentHandler)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }
    protected static String generateLinkCode(Class<?> clazz) {
        return String.format("link:common/%s.html[Enum,role=\"popup\"]", clazz.getSimpleName().toLowerCase());
    }

}


