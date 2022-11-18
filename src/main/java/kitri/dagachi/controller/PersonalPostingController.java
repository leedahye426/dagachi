package kitri.dagachi.controller;

//import kitri.dagachi.service.FileService;

//import jdk.internal.jimage.ImageReader;

import kitri.dagachi.SessionConstants;
import kitri.dagachi.model.Member;
import kitri.dagachi.model.Post;
import kitri.dagachi.model.PostingLike;
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

    //select
    @GetMapping("/post/person/person_post")
    public String enterPosting(Model model) {

        List<Post> post = postservice.posting();
//        List<PostTags> tag = postservice.tag();

//        PageRequest pageRequest = PageRequest.of(page, 3,Sort.by(Sort.Direction.DESC, "postingId"));
//        Page<Post> post = postRepository.findAll(pageRequest);

        model.addAttribute("post", post);
//        model.addAttribute("tag",tag);

        System.out.println(post);

        return "/post/per/personalPosting";
    }


    //    공고보기 클릭 시 상세페이지 이동
    @GetMapping("/post/person/{postingId}/detail")
    public String postingDetail(@ModelAttribute("postingId") Long postingId, Model model) {
        Post post = postservice.findOne(postingId);
        String companyName = post.getCompanyName();
        String postingTitle = post.getPostingTitle();
        String postingContent = post.getPostingContent();

        model.addAttribute("post", post);

        return "post/per/personalDetail";
    }

    @PostMapping("/post/person/like")
    @ResponseBody
    public String like(@RequestParam Long postingId, HttpSession session, Model model, @AuthenticationPrincipal Member member) {


        PostingLike postinglike = new PostingLike();
        // respoisotiry.insert
        postinglike.setPostingId(postingId);

        //session 값 가져오기
        Long memberId = member.getId();
        postinglike.setMemberId(memberId);


        postservice.save(postinglike);


        System.out.println("postinglike.getPostingId() : " + postinglike.getPostingId());
        System.out.println("postinglike.getMemberId() : " + postinglike.getMemberId());
        System.out.println("postinglike.getRowNum() : " + postinglike.getRowNum());


        return "";
    }


}





