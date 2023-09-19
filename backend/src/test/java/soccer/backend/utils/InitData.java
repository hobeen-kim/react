package soccer.backend.utils;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import soccer.backend.auth.entity.Authority;
import soccer.backend.auth.entity.Member;
import soccer.backend.domain.post.entity.Comment;
import soccer.backend.domain.post.entity.Post;

@Component
@RequiredArgsConstructor
public class InitData {

    private final EntityManager em;

    public void initPost(){

        Member member1 = new Member("test@test.com", "testId", "testPw", "testName", "testNickname", Authority.ROLE_USER);
        Member member2 = new Member("test2@test.com", "test2Id", "test2Pw", "test2Name", "test2Nickname", Authority.ROLE_USER);

        for (int i = 1; i <= 100; i++) {
            Member member = i % 2 == 0 ? member1 : member2;
            Post post = Post.createPost(member, "testTitle" + i, "testContent" + i);
            for(int j = 1; j <= 10; j++){
                Comment.createComment(member, post, "testComment" + i + "-" + j);
            }
        }

        em.persist(member1);
        em.persist(member2);
    }
}
