package kitri.dagachi.controller;

//import kitri.dagachi.service.FileService;
//import kitri.dagachi.dto.PostDto;
//import kitri.dagachi.dto.PostFileDto;
import kitri.dagachi.model.Member;
import kitri.dagachi.model.Post;
import kitri.dagachi.service.postService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
//@RequestMapping
public class EnterPostingController {

    @Autowired
    private final postService postservice;


//    //select
//    @GetMapping("/post/enterprise/post_list")
//   // @EnableGlobalMethodSecurity
//    public String enterPosting(Model model) {
//
//        List<Post> post = postservice.posting();
//
//        model.addAttribute("post", post);
//
//        System.out.println(post);
//
//        return "/post/post_list";
//    }



    //approve 된 게시글만 보이게
    @GetMapping("/post/enterprise/post_list")
    public String posting(Model model, @AuthenticationPrincipal Member member)
    {

        List<Post> post = postservice.approveList();
        Long memberId = member.getId();


        model.addAttribute("memberId", memberId);
        model.addAttribute("post", post);



        return "/post/post_list";

    }




    //insert

    @PostMapping("/post/enterprise/post_approve_list")
    public String postingRegister(Post post, String[] tag, @AuthenticationPrincipal Member member) {


        Long memberId = member.getId();
        post.setMemberId(memberId);

        String companyName = member.getName();
        post.setCompanyName(companyName);


        System.out.println(tag[0]);
        System.out.println(tag[1]);
        System.out.println(member.getName());


        postservice.register(post, tag);


            return "redirect:/post/enterprise/post_approve_list";
    }


    //공고등록하기 버튼 클릭시
    @GetMapping("/post/enterprise/post_register_form")
    public String postingRegisterForm(Post post, Model model, @AuthenticationPrincipal Member member)
    {
        Long memberId = member.getId();

        System.out.println(memberId);

//        List<Member> members = postservice.findByName(memberId);

        String companyName = member.getName();
        System.out.println("==============="+ member.getName());

        model.addAttribute("memberId",memberId);
        model.addAttribute("companyName",companyName);

        return "/post/post_register_form";
    }




    //공고보기 클릭 시 상세페이지 이동
    @GetMapping("/post/enterprise/post_detail/{postingId}")
    public String postingDetail(@ModelAttribute("postingId") Long postingId, Model model, @AuthenticationPrincipal Member member) {
        Post post = postservice.findOne(postingId);
        String companyName = post.getCompanyName();
        String postingTitle = post.getPostingTitle();
        String postingContent = post.getPostingContent();
        Long loginId = member.getId();


        model.addAttribute("memberId", loginId);
        model.addAttribute("post", post);

        return "/post/post_detail";
    }


    //삭제버튼 클릭시
    @GetMapping("/post/enterprise/delete/{postingId}")
    public String postDelete(@PathVariable Long postingId) {
        System.out.println("postingId: " + postingId);
        postservice.delete(postingId);


        return "redirect:/post/enterprise/post_list";
    }


    //작성글

    @GetMapping("/post/enterprise/list")
        public String list(Model model, @AuthenticationPrincipal Member member)
        {

            Long memberId = member.getId();
            List<Post> list = postservice.findlist(memberId);
            List<Post> postList = new ArrayList<>();

            for (Post p : list){
                postList.add(postservice.findById(p.getPostingId()));
            }


            model.addAttribute("post",postList);

            return "/post/post_write_list";
        }


        //내 채용신청 글 보기
        @GetMapping("/post/enterprise/post_approve_list")
    public String myapprove(@AuthenticationPrincipal Member member, Model model)
        {
            Long memberId = member.getId();;
            List<Post> list = postservice.findlist(memberId);
            List<Post> approvelist = new ArrayList<>();

            for(Post p : list)
            {
//                p.setCompanyName(member.getName());
                approvelist.add(postservice.findById(p.getPostingId()));
            }
//            String companyName = member.getName();

//            model.addAttribute("companyName",companyName);
            model.addAttribute("memberId", memberId);
            model.addAttribute("post",approvelist);

            return "/post/post_approve_list";

        }


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
