package soccer.backend.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.backend.auth.service.MemberService;
import soccer.backend.auth.entity.Member;
import soccer.backend.domain.post.dto.servicedto.CommentServiceDto;
import soccer.backend.domain.post.entity.Comment;
import soccer.backend.domain.post.entity.Post;
import soccer.backend.domain.post.repository.CommentRepository;
import soccer.backend.global.exception.BusinessException;
import soccer.backend.global.exception.ExceptionCode;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final PostService postService;

    /**
     * 댓글 생성
     * @param dto : 게시글 생성에 필요한 정보
     * @return 생성된 게시글
     */
    @Transactional
    public Long createComment(Long postId, CommentServiceDto dto){

        Comment comment = Comment.createComment(getCurrentMember(), getPost(postId), dto.getContent());
        commentRepository.save(comment);
        return comment.getId();
    }

    /**
     * 게시글에 대한 댓글 목록 조회 (페이징), 댓글 1개만 조회하는 건 일단 private
     * @param postId : 댓글 목록을 조회할 게시글 id
     * @param pageable : 페이징 정보
     * @return : 페이징된 댓글 목록
     */
    public Page<Comment> getComments(Long postId, Pageable pageable){
        return commentRepository.findAllByPaging(postId, pageable);
    }

    /**
     * 댓글 1개 조회
     * @param commentId : 조회할 댓글 id
     * @return : 조회된 댓글
     */
    public Comment getComment(Long commentId){
        return findComment(commentId);
    }

    /**
     * 댓글 수정
     * @param commentId : 수정할 댓글 id
     * @param dto : 수정할 댓글 정보
     * @return : 수정된 댓글 id
     */
    @Transactional
    public Long updateComment(Long commentId, CommentServiceDto dto){
        return getComment(commentId).updateComment(getCurrentMember(), dto.getContent());
    }

    /**
     * 댓글 삭제
     * @param commentId : 삭제할 댓글 id
     */
    @Transactional
    public void deleteComment(Long commentId){
        delete(commentId);
    }

    /**
     * 댓글이 달리는 게시글 조회
     * @param postId : 조회할 게시글 id
     * @return : 조회된 게시글
     */
    private Post getPost(Long postId) {
        return postService.getPost(postId);
    }

    /**
     * 댓글 1개 조회
     * @param commentId : 조회할 댓글 id
     * @return : 조회된 댓글
     */
    private Comment findComment(Long commentId) {
        return commentRepository.findByIdWithMember(commentId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.COMMENT_NOT_FOUND));
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
     * 댓글 삭제 메서드
     * @param id : 삭제할 댓글 id
     */
    private void delete(Long id) {
        if(!isDeleted(id)){
            throw new BusinessException(ExceptionCode.ACCESS_DENIED);
        }
    }

    /**
     * 댓글 삭제 쿼리 실행 후 결과값 반환
     * @param id : 삭제할 댓글 id
     * @return : 삭제되었는지 여부
     */
    private boolean isDeleted(Long id) {
        return commentRepository.deleteByIdWithMemberId(id, getCurrentMemberId()) != 0;
    }
}
