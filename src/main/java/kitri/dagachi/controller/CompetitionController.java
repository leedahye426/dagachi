package kitri.dagachi.controller;

import java.util.*;
import kitri.dagachi.model.Competition;
import kitri.dagachi.service.CompetitionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionService competitionService;

    @GetMapping("/competition/admin/competition_list")
    public String list(Model model) {
        List<Competition> competitions = competitionService.findAll();
        model.addAttribute(competitions);
        return "competition/competition_list";
    }
}
