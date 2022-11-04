package kitri.dagachi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostingController {
    @GetMapping("/enter/enter_post")
    public String Posting() {

        return "enterPosting";

    }
}
