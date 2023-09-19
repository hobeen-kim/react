package soccer.backend.domain.post.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import soccer.backend.utils.InitData;
import soccer.backend.domain.post.entity.Post;

import java.util.List;

@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired InitData initData;
    @Autowired PostRepository postRepository;
    @Autowired EntityManager em;

    @Test
    public void findAllByPaging() {
        initData.initPost();
        List<Post> result = postRepository.findAllByPaging(PageRequest.of(1, 10)).getContent();

        for (Post post : result) {
            System.out.println("post = " + post);
            System.out.println("post.getMember() = " + post.getMember().getName());
        }




    }


}