package kitri.dagachi.controller;


import kitri.dagachi.model.Member;
import kitri.dagachi.model.Post;
import kitri.dagachi.model.PostingLike;
import kitri.dagachi.service.postService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class PostApproveController {

    @Autowired
    private final postService postservice;


    //채용공고 리스트
    @GetMapping("/post/admin/approve_list")
    public String application(Model model)
    {

        List<Post> approve = postservice.posting();
        model.addAttribute("post",approve);
        return "/post/post_approve_list";
    }

//    @GetMapping("/post/admin/post_approve/{postingId}")
//    public String approve(@PathVariable Long postingId,Model model)
//    {
//        postservice.updateApprove(postingId);
////        post.getApprove();
//      List<Post> approveList = postservice.posting();
//        model.addAttribute("post", approveList);
//        return "redirect:/post/admin/approve_list";
//    }
//
//    @GetMapping("/post/admin/post_approve_cancel/{postingId}")
//    public String approveCancel(@PathVariable Long postingId,Model model)
//    {
//        postservice.updateApprove(postingId);
////        post.getApprove();
//        List<Post> approveList = postservice.posting();
//        model.addAttribute("post", approveList);
//        return "redirect:/post/admin/approve_list";
//    }





    //승인 detail
    @GetMapping("/post/admin/post_approve_detail/{postingId}")
    public String approveDetail(@PathVariable("postingId") Long postingId, Model model)
    {
        Post post = postservice.findOne(postingId);

        model.addAttribute("post",post);

        return "/post/post_approve_detail";
    }

    //승인버튼
//    @GetMapping("/post/admin/post_approve/{postingId}")
//    public String approve(@PathVariable("postingId") Long postingId,Model model) {
//
//        Post post = postservice.findOne(postingId);
//        postservice.updateApprove(post);
////        post.getApprove();
//        List<Post> approve = postservice.posting();
//        model.addAttribute("post", approve);
//
//        return "redirect:/post/admin/approve_list";
//
//    }

    //승인


    @GetMapping("/post/admin/post_approve/{postingId}")

    public String approve(@PathVariable Long postingId, Model model)
    {

        System.out.println(postingId);



        postservice.saveApprove(postingId);
        List<Post> post = postservice.posting();
        model.addAttribute("post",post);
//        System.out.println(post.getApprove());
//        post.setApprove("N");




        return "redirect:/post/admin/approve_list";



    }


    //승인 취소


//    @PostMapping("/post/admin/post_approve_cancel/{postingId}")
//    @ResponseBody

    @GetMapping("/post/admin/post_approve_cancel/{postingId}")
    public String approveCancel(@PathVariable Long postingId, Model model) {

        postservice.cancelApprove(postingId);
        List<Post> post = postservice.posting();

        model.addAttribute("post",post);




        return "redirect:/post/admin/approve_list";
    }
//    @PostMapping("/post/admin/post_approve")
//    @ResponseBody




}
