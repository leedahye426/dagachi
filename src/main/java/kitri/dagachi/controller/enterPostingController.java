package kitri.dagachi.controller;

//import kitri.dagachi.service.FileService;
import kitri.dagachi.model.Post;
import kitri.dagachi.repository.PostRepository;
import kitri.dagachi.service.postService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class enterPostingController {

    @Autowired
    private final postService postservice;
    private final PostRepository postRepository;



//    메인페이지에서 채용공고 메뉴 클릭 시
//    @GetMapping("/enter/enter_post")
//    public String posting(){
//
//        return "/post/enterPosting";
//    }


//select
    @GetMapping("/post/enter/enter_post")
    public String enterPosting(Model model)
    {

        List<Post> post = postservice.posting();
//        List<PostTags> tag = postservice.tag();


        model.addAttribute("post",post);
//        model.addAttribute("tag",tag);

        System.out.println(post);

        return "/post/enterPosting";
    }





    //insert

    @PostMapping("/post/enter/enter_post")
    public String postingRegister(Post post, String[] tag) {

        //        System.out.println("===================="+postTags);

        System.out.println(tag[0]);
        System.out.println(tag[1]);


        postservice.register(post, tag);

        return "/post/enterPosting";
    }




    //공고등록하기 버튼 클릭시
    @GetMapping("/post/enter/register")
    public String postingRegisterForm(){
        return "/post/postingRegisterForm";
    }




    //공고보기 클릭 시 상세페이지 이동
    @GetMapping("/post/enter/{postingId}/detail")
    public String postingDetail(@ModelAttribute("postingId") Long postingId,  Model model)
    {
            Post post = postservice.findOne(postingId);
            String companyName = post.getCompanyName();
             String postingTitle = post.getPostingTitle();
             String postingContent = post.getPostingContent();

            model.addAttribute("post",post);

            return "post/enterDetail";
    }



//    @GetMapping("/post/enter/(id={post.postingId})/delete")
//    public String delete(Long postingId)
//    {
//        postservice.delete(postingId);
//        return "redirect:/post/enterPosting";
//    }
//    @PostMapping("post/enter/delete")
//    public String delete(Long postingId, RedirectAttributes ra)
//    {
//        ra.addFlashAttribute("posingId",postingId);
//        return "redirect:/post/enterPosting";
//    }



//    @PostMapping("/post/enter/delete/{postingId}")
//    public String postDelete(@PathVariable ("postingId") Long postingId)
//    {
//        System.out.println("postingId: " + postingId);
//        int result=  postservice.delete(postingId);
//        System.out.println("result:" + result);
////        ra.addFlashAttribute("postingId",postingId);
//
//        return "redirect:/post/enterPosting";
//    }


    @GetMapping("/post/enter/delete/{postingId}")
    public String postDelete(@PathVariable Long postingId)
    {
        System.out.println("postingId: " + postingId);
         postservice.delete(postingId);

//        ra.addFlashAttribute("postingId",postingId);

        return "redirect:/post/enter/enter_post";
    }



    //    @PostMapping("/enter/delete")
//    public String delete(Long postingId, RedirectAttributes ra)
//    {
//        ra.addFlashAttribute("posingId",postingId);
//        return "redirect:/post/personalPosting";
//    }
}


































//        post.setPosting_Content(post.getPosting_Content());
//        post.setPosting_Title(post.getPosting_Title());
//      post.setUpload_Date(post.getUpload_Date());
//        post.setCompany_Name(post.getCompany_Name());
//        post.setMember_Id(post.getMember_Id());
//     System.out.println(postForm.getCompany_Name());


//        postRepository.findAll();



//        @PostMapping("enter/register")
//    public String updateItem(@ModelAttribute("form") PostForm form) {
//
//        postservice.upload();
//
//        return "/post/enterPosting";
//    }


//    @GetMapping("/enter/upload")
//    public String updateItem(Model model) {
//       List<PostForm> postFormList = Post.selectList("post.selectPostList");
//
//       for(PostForm pf : postFormList){
//           System.out.println(pf);






//    postRepository.findAll();
//        postservice.upload();

//
//    }


//    @PostMapping("enter/register")
//    public String updateItem(@PathVariable Long Posting_ID, @ModelAttribute("form") PostForm form) {
//
//        postservice.findPostings(Posting_ID, form.getCompany_Name(), form.Posting_Content(), form.getPosting_Title(),form.getUpload_Date(),form.getMember_ID());
//
//        return "/post/enterPosting";
//    }

//
//        postForm.setPosting_ID(postForm.getPosting_ID());
//        postForm.setPosting_Content(postForm.getPosting_Content());
//        postForm.setPosting_Title(postForm.getPosting_Title());
//        postForm.setUpload_Date(postForm.getUpload_Date());
//        postForm.setCompany_Name(postForm.getCompany_Name());
//        postForm.setMember_ID(postForm.getMember_ID());

//        postservice.savePost(postForm);





 /*   @GetMapping("/enter/post")
    public String createForm(Model model) {
        model.addAttribute("PostForm", new PostForm());
        return "/enter/";
    }*/



//    @GetMapping("/enter/enter_post")
//    public String list(Model model) {
//        List<PostForm> postForms = postservice.findPostings();
//        model.addAttribute("postForm", postForms);
//        return "post/enterPosting";
//    }
//}

//    @PostMapping("/enter/")
//            public String create(PostForm postForm)
//    {
//        postForm.setPosting_ID(postForm.getPosting_ID());
//        postForm.setPosting_Content(postForm.getPosting_Content());
//        postForm.setPosting_Title(postForm.getPosting_Title());
//        postForm.setUpload_Date(postForm.getUpload_Date());
//        postForm.setCompany_Name(postForm.getCompany_Name());
//        postForm.setMember_ID(postForm.getMember_ID());
//     System.out.println(postForm.getCompany_Name());
//} postForm.setPosting_ID(postForm.getPosting_ID());







//    @RequestMapping("/enter/register")
//    @PostMapping("/enter/enter_post")

//            public String submit(HttpServletRequest httpServletRequest, Model model)
//    {
//        String Company_Name = httpServletRequest.getParameter("Company_Name");
//        String Upload_Date = httpServletRequest.getParameter("Upload_Date");
//        String Posting_Content = httpServletRequest.getParameter("Posting_Content");
//        String Posting_Title = httpServletRequest.getParameter("Posting_Title");
//        String Member_Id =httpServletRequest.getParameter("Member_Id");
//
//        model.addAttribute("Company_Name",Company_Name);
//        model.addAttribute("Upload_Date",Upload_Date);
//        model.addAttribute("Posting_Content",Posting_Content);
//        model.addAttribute("Posting_Title",Posting_Title);
//        model.addAttribute("Member_Id",Member_Id);
//
//        //insert
//
//
//
//        return "/post/enterPosting";
//    }
//}


//        Post post = new Post();
//        post.setCompany_Name(form.getCompany_Name());
//        post.setPosting_Content(form.getPosting_Content());
////        post.setPosting_Title(form.getPosting_Title());
////        post.setMember_Id(form.getMember_ID());
//          post.setUpload_Date(form.getUpload_Date());
//
//
//
//        postForm.setPosting_ID(postForm.getPosting_ID());
//        postForm.setPosting_Content(postForm.getPosting_Content());
//        postForm.setPosting_Title(postForm.getPosting_Title());
//        postForm.setUpload_Date(postForm.getUpload_Date());
//        postForm.setCompany_Name(postForm.getCompany_Name());
//        postForm.setMember_ID(postForm.getMember_ID());
//        System.out.println(postForm.getCompany_Name());
//



//    @GetMapping("/enter/post")
//    public String list(Model model) {
//        List<Post> post = postService.findPost();
//        model.addAttribute("post",post);
//    return "post/enterPosting";
//    }
//}


//    @GetMapping("/enter/enter_post")
//    public String register() {
//
//
//        return "/post/enter_register";
//
//    }


//    @PostMapping("/enter/enter_post")
//            public String addImage(@RequestParam PostForm form, HttpServletRequest request)
//    {
//        Post post = new Post();
//        post.setCompany_Name(form.getCompany_Name());
//        post.setUpload_Date(form.getUpload_Date());
//        post.setPosting_Content(form.getPosting_Content());
//        post.setPosting_Title(form.getPosting_Title());
//        post.setMember_Id(form.getMember_ID());
//
//        return "업로드 완료";
//    }
//}


//    {
//
//        return "enterPosting";
//
//=======
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class PostingController {
//
//    @GetMapping("/enter/enter_post")
//    public String project() {
//        return "enterPosting";
//    }
//>>>>>>> 2c7590eed672a2d85bf1b042e1fa46725ca25c4b
//}