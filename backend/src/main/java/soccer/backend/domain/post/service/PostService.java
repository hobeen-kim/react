package soccer.backend.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.backend.auth.service.MemberService;
import soccer.backend.auth.entity.Member;
import soccer.backend.domain.post.dto.servicedto.PostServiceDto;
import soccer.backend.domain.post.entity.Post;
import soccer.backend.domain.post.repository.PostRepository;
import soccer.backend.global.exception.BusinessException;
import soccer.backend.global.exception.ExceptionCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;

    /**
     * 게시글 생성
     * @param dto : 게시글 생성에 필요한 정보
     * @return 생성된 게시글
     */
    @Transactional
    public Long createPost(PostServiceDto dto){

        Post post = Post.createPost(getCurrentMember(), dto.getTitle(), dto.getContent());
        postRepository.save(post);

        return post.getId();
    }

    /**
     * 게시글 1개 조회
     * @param id : 조회할 게시글 id
     * @return : 조회된 게시글
     */
    public Post getPost(Long id){

        return findPostWithMember(id);
    }

    /**
     * 게시글 목록 조회
     * @param pageable : 페이징 정보
     * @return : 페이징된 게시글 목록
     */
    public Page<Post> getPosts(Pageable pageable){

        return findAllPosts(pageable);
    }

    /**
     * 게시글 수정
     * @param dto : 게시글 수정에 필요한 정보
     * @return : 수정된 게시글 id
     */
    @Transactional
    public Post updatePost(Long postId, PostServiceDto dto){

        return findPost(postId)
                .updatePost(getCurrentMember(), dto.getTitle(), dto.getContent()
                );

    }

    /**
     * 게시글 삭제 (리턴 없음)
     * @param id : 삭제할 게시글 id
     */
    @Transactional
    public void delete(Long id){
        deletePost(id);
    }

    /**
     * 현재 로그인된 사용자
     * @return : 현재 로그인된 사용자
     */
    private Member getCurrentMember(){
        return memberService.getCurrentMember();
    }

    /**
     * 현재 로그인된 사용자 id
     * @return : 현재 로그인된 사용자 id
     */
    private Long getCurrentMemberId(){
        return memberService.getCurrentMemberId();
    }

    /**
     * 게시글 페이징 조회 기능
     * @param pageable : 페이징 정보
     * @return : 페이징된 게시글 목록
     */
    private Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAllByPaging(pageable);
    }

    /**
     * 게시글 조회 기능
     * @param id : 조회할 게시글 id
     * @return : 조회된 게시글
     */
    private Post findPost(Long id){
        return postRepository.findById(id).orElseThrow(
                () -> new BusinessException(ExceptionCode.POST_NOT_FOUND)
        );
    }

    /**
     * 게시글과 댓글, 유저 조회 기능(fetch join)
     * @param id : 조회할 게시글 id
     * @return : 조회된 게시글
     */
    private Post findPostWithMember(Long id) {
        return postRepository.findByIdWithMember(id).orElseThrow(
                () -> new BusinessException(ExceptionCode.POST_NOT_FOUND)
        );
    }

    /**
     * 게시글 삭제 기능, 현재 로그인된 사용자가 작성한 게시글만 삭제 가능
     * @param id : 삭제할 게시글 id
     */
    private void deletePost(Long id) {
        if(isDeleted(id)){
            throw new BusinessException(ExceptionCode.ACCESS_DENIED);
        }
    }

    /**
     * 댓글 삭제 쿼리 실행 후 결과값 반환
     * @param id : 삭제할 게시물 id
     * @return : 삭제되었는지 여부
     */
    private boolean isDeleted(Long id) {
        return postRepository.deleteByIdWithMemberId(id, getCurrentMemberId()) == 0;
    }
}
