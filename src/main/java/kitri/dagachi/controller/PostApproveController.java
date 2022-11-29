package kitri.dagachi.controller;


import kitri.dagachi.model.Post;
import kitri.dagachi.service.postService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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



    @GetMapping("/post/admin/approve/{postingId}")
    public String updateApprove(@PathVariable("postingId") Long postingId, Model model)
    {
       postservice.approve(postingId);

//        if(post.getApprove() == null)
//        {
//            post.setApprove("Y");
//        }
//        else
//        {
//            post.setApprove("N");
//        }
        List<Post> approveList = postservice.posting();
        model.addAttribute("post",approveList);




        return "redirect:/post/admin/approve_list";
    }



}
