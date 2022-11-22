package kitri.dagachi.controller;

import java.util.*;
import kitri.dagachi.model.Competition;
import kitri.dagachi.service.CompetitionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionService competitionService;

    @GetMapping("/competition/admin/competition_list")
    public String list(Model model) {
        List<Competition> competitions = competitionService.findAllCompetition();
        model.addAttribute("competitions",competitions);
        return "competition/competition_list";
    }

    @GetMapping("/competition/admin/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Competition competition = competitionService.findOne(id);
        model.addAttribute("competition", competition);

        return "competition/competition_detail";
    }

    @GetMapping("/competition/admin/register")
    public String register() {
        return "competition/competition_register";
    }


}
