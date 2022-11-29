package kitri.dagachi.controller;

//import kitri.dagachi.service.FileService;

//import jdk.internal.jimage.ImageReader;

import kitri.dagachi.SessionConstants;
import kitri.dagachi.model.Member;
import kitri.dagachi.model.Post;
import kitri.dagachi.model.PostingLike;
import kitri.dagachi.repository.MemberRepository;
import kitri.dagachi.repository.PostLikeRepository;
import kitri.dagachi.repository.PostRepository;
import kitri.dagachi.service.postService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.Color.gray;
import static java.awt.Color.red;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class PersonalPostingController {


    private final EntityManager em;
    @Autowired
    private final postService postservice;
    private final PostRepository postRepository;
    private final PostLikeRepository postlikerepository;

    //select
//    @GetMapping("/post/personal/post_list")
//    public String enterPosting(Model model, @AuthenticationPrincipal Member member) {
//
//        List<Post> post = postservice.posting();
//        Long memberId = member.getId();
//
//        model.addAttribute("memberId", memberId);
//        model.addAttribute("post", post);
//
//
//        System.out.println(post);
//
//        return "/post/post_list";
//    }

    //approve 된 게시글만 보이게
    @GetMapping("/post/personal/post_list")
      public String posting(Model model, @AuthenticationPrincipal Member member)
    {

        List<Post> post = postservice.approveList();
        Long memberId = member.getId();

//        System.out.println();


        model.addAttribute("memberId", memberId);
        model.addAttribute("post", post);



        return "/post/post_list";

    }

    //    공고보기 클릭 시 상세페이지 이동
    @GetMapping("/post/personal/{postingId}/post_detail")

    public String postingDetail(@ModelAttribute("postingId") Long postingId, Model model,@AuthenticationPrincipal Member member) {
        Post post = postservice.findOne(postingId);
        String companyName = post.getCompanyName();
        String postingTitle = post.getPostingTitle();
        String postingContent = post.getPostingContent();
        Long memberId = member.getId();

        System.out.println(postingId+memberId);

        Long cnt = postservice.likecnt(postingId, memberId);

        System.out.println("cnt" + cnt);

        model.addAttribute("cnt", cnt);
        model.addAttribute("post", post);

        return "/post/post_detail";
    }

    //검색
    @GetMapping("/post/personal/post_search")
    public String search(String keyword, Model model)
    {

        List<Post> searchList = postservice.search(keyword);

        model.addAttribute("post",searchList);

        return "/post/post_list";
    }









    //좋아요
    @PostMapping("/post/personal/fill_like")
    @ResponseBody
    public String fill_like(@RequestParam Long postingId, HttpSession session, Model model, @AuthenticationPrincipal Member member) {


        PostingLike postinglike = new PostingLike();
        // respoisotiry.insert
        postinglike.setPostingId(postingId);

        //session 값 가져오기
        Long memberId = member.getId();
        postinglike.setMemberId(memberId);


        System.out.println("postinglike.getPostingId() : " + postinglike.getPostingId());
        System.out.println("postinglike.getMemberId() : " + postinglike.getMemberId());
        System.out.println("postinglike.getRowNum() : " + postinglike.getRowNum());



        postservice.save(postinglike);





        return "";
    }

    //좋아요 취소
    @PostMapping("/post/personal/empty_like")
    @ResponseBody
    public String empty_like(@RequestParam Long postingId, HttpSession session, Model model, @AuthenticationPrincipal Member member)
    {
        Long memberId = member.getId();


        PostingLike postinglike = postservice.findlike(postingId, memberId);

        System.out.println("posingId : " + postingId);
        System.out.println("memberId : " + memberId);

        postservice.likeedel(postinglike);

        return "";

    }


    //찜목록
    @GetMapping("/post/personal/like_list")
    public String likeList(Model model, @AuthenticationPrincipal Member member)
    {
        Long memberId= member.getId();
        List<PostingLike> postingLikes = postservice.likeall(memberId);
        List<Post> post = new ArrayList<>(); // 배열 초기화


        for (PostingLike p : postingLikes){
            post.add(postservice.findById(p.getPostingId()));
        }

        model.addAttribute("post",post); // post_list에 보내기 때문에 같은 타입으로 보내야 함. post



            return "/post/post_list";




    }




}





