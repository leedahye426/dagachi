package kitri.dagachi.controller;

//import kitri.dagachi.service.FileService;

//import jdk.internal.jimage.ImageReader;

import kitri.dagachi.SessionConstants;
import kitri.dagachi.model.Member;
import kitri.dagachi.model.Post;
import kitri.dagachi.model.PostingLike;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class PersonalPostingController {

    @Autowired
    private final postService postservice;
    private final PostRepository postRepository;
    private final PostLikeRepository postlikerepository;

    //select
    @GetMapping("/post/personal/post_list")
    public String enterPosting(Model model) {

        List<Post> post = postservice.posting();


        model.addAttribute("post", post);


        System.out.println(post);

        return "/post/post_list";
    }


    //    공고보기 클릭 시 상세페이지 이동
    @GetMapping("/post/personal/{postingId}/detail")
    public String postingDetail(@ModelAttribute("postingId") Long postingId, Model model) {
        Post post = postservice.findOne(postingId);
        String companyName = post.getCompanyName();
        String postingTitle = post.getPostingTitle();
        String postingContent = post.getPostingContent();

        model.addAttribute("post", post);

        return "/post/post_detail";
    }

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


}





