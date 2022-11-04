package kitri.dagachi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class PostingController {
    @GetMapping("/dagachi/daca")
    public String dagachi(Model model)
    {

        return "enterPosting";

}
