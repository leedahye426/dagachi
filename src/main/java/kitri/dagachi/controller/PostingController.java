package kitri.dagachi.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping
public class PostingController {
    @GetMapping("/enter/enter_post")
<<<<<<< HEAD
    public String posting(Model model) {
        model.addAttribute("form",new PostForm());
=======
    public String Posting() {

>>>>>>> e65b1f77a2bf8f0cd2187b48db38d225a4a5119f
        return "enterPosting";

    }
}

//    @PostMapping("/enter/enter_post")
//            public String addImage(@RequestParam("photo")MultipartFile uploadFile, HttpServletRequest request)
//    {
//
//        return "업로드 완료"
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
