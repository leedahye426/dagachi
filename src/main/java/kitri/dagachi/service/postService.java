package kitri.dagachi.service;

//import kitri.dagachi.dto.PostDto;
//import kitri.dagachi.dto.PostFileDto;
import kitri.dagachi.model.*;
//import kitri.dagachi.repository.FileRepository;
import kitri.dagachi.repository.PostLikeRepository;
import kitri.dagachi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class postService {

    @Autowired
    private final PostRepository postRepository;
    private final PostLikeRepository postlikerepository;

    private final EntityManager em;


    //글 작성 처리(insert)
    public void register(Post post, String[] tag) {


        postRepository.save(post);


        Long postingId = post.getPostingId();
        for (int i = 0; i < tag.length; i++) {
            PostTags postTags = new PostTags();
            postTags.setTag(tag[i]);
            postTags.setPostingId(postingId);
            postRepository.savaposttag(postTags);

        }

    }

    //좋아요 저장(insert)
    public void save(PostingLike postinglike) {

        postRepository.savaLike(postinglike);
    }


    //좋아요 취소
    @Transactional
    public void likeedel(PostingLike postinglike) {

        postlikerepository.del(postinglike);
        System.out.println("삭제되라");

    }

    //리스트처리
    public List<Post> posting() {


        return postRepository.findAll();

    }

    //승인 후 게시글 보여주기
    public List<Post> approveList()
    {
        return postRepository.findApprove();

    }

    //tag검색
    public void tags(Post post, String[] tag)
    {
        Long postingId = post.getPostingId();
        for (int i = 0; i < tag.length; i++) {
            PostTags postTags = new PostTags();
            postTags.setTag(tag[i]);
            postTags.setPostingId(postingId);

            postRepository.findtags(postingId);

        }


    }

    //like 수 보여주기
    public void likeCnt(Long postingId, Long cnt)
    {
//        Post like = postRepository.findOne(postingId);
////        Long n = postlikerepository.cnt(postingId);
////        like.setCnt(n+cnt);

        postRepository.cnt(postingId, cnt);

        System.out.println("=============="+cnt);
//        System.out.println("=============="+n);


    }



    //삭제
    public void delete(Long postingId) {

        postRepository.delete(postingId);
        System.out.println("==============" + postingId);
    }


    //detail 처리 시
    public Post findOne(Long postingId) {
        return postRepository.findOne(postingId);
    }

    //검색
    @Transactional
    public List<Post> search(String keyword){
        return  postRepository.findByTitleContaining(keyword);

    }

    //작성 글 보여주기
    public List<Post> findlist(Long memberId)
    {
        return postRepository.findById(memberId);
    }


    // 여기서부터는 like

    public PostingLike findMid(Long memberId) {
        return postlikerepository.findMID(memberId);
    }


    public PostingLike findlike(Long postingId, Long memberId) {
        return postlikerepository.findLike(postingId, memberId);
    }






    //새로고침 시 하트 유지 하기 위한 cnt

    public Long likecnt(Long postingId, Long memeberId)
    {
        return postlikerepository.likecnt(postingId, memeberId);
    }

    public List<PostingLike> likeall(Long memberId)
    {
        return postlikerepository.LikeAll(memberId);
    }

    public Post findById(Long postingId)
    {
        return postlikerepository.findById(postingId);
    }


    public PostTags findByOne(Long postingId)
    {
        return postlikerepository.findByOne(postingId);
    }

    @Transactional
    //approve
//    public void updateApprove(Post post)
//    {
//        postRepository.approve(post);
//
//
//    }


    public void saveApprove(Long postingId) {

       postRepository.approve(postingId);

    }

    public void cancelApprove(Long postingId) {

        postRepository.approveCancel(postingId);

    }


}


