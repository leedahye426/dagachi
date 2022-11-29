package kitri.dagachi.service;

//import kitri.dagachi.dto.PostDto;
//import kitri.dagachi.dto.PostFileDto;
import kitri.dagachi.model.*;
//import kitri.dagachi.repository.FileRepository;
import kitri.dagachi.repository.PostLikeRepository;
import kitri.dagachi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.id.PostInsertIdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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


//        postRepository.LOGO(path,filename);
    }

    //좋아요 저장(insert)
    public void save(PostingLike postinglike) {

        postRepository.savaLike(postinglike);
    }

    //파일

//    public void savaPost(PostDto postDto)
//    {
//        postRepository.savaPost(postDto);
//    }


    //리스트처리
    public List<Post> posting() {


        return postRepository.findAll();

    }

    //승인 후 게시글 보여주기
    public List<Post> approveList()
    {
        return postRepository.findApprove();
    }


    //승인버튼 클릭시 Y로 변경

    public void approve(Long postingId){


       postRepository.updateApprove(postingId);



    }


    //삭제
    public void delete(Long postingId) {

        postRepository.delete(postingId);
        System.out.println("==============" + postingId);
    }



    public Post findOne(Long postingId) {
        return postRepository.findOne(postingId);
    }

    //검색
    @Transactional
    public List<Post> search(String keyword){
        return  postRepository.findByTitleContaining(keyword);

    }



    // 여기서부터는 like

    public PostingLike findMid(Long memberId) {
        return postlikerepository.findMID(memberId);
    }


    public PostingLike findlike(Long postingId, Long memberId) {
        return postlikerepository.findLike(postingId, memberId);
    }

    //작성 글 보여주기
    public List<Post> findlist(Long memberId)
    {
        return postRepository.findById(memberId);
    }















    //파일
//    @Transactional
//    public PostDto getPost(Long id){
//
//        Post post = postRepository.findById(id); //fileId불러오기
//
//        PostDto postdto = PostDto.builder()
//                        .id(post.getPostingId())
//                        .companyName(post.getCompanyName())
//                        .postingTitle(post.getPostingTitle())
//                        .postingContent(post.getPostingContent())
//                        .uploadDate(post.getUploadDate())
//                        .fileId(post.getFileId())
//                        .build();
//
//        return postdto;
//    }


    //좋아요 취소
    @Transactional
    public void likeedel(PostingLike postinglike) {

        postlikerepository.del(postinglike);
        System.out.println("삭제되라");

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


}


