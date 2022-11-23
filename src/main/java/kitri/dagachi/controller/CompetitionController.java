package kitri.dagachi.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import kitri.dagachi.model.Competition;
import kitri.dagachi.model.Member;
import kitri.dagachi.service.CompetitionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionService competitionService;

    @GetMapping("/competition/competition_list")
    public String list(Model model) {
        List<Competition> competitions = competitionService.findAllCompetition();
        model.addAttribute("competitions",competitions);
        return "competition/competition_list";
    }

    @GetMapping("/competition/detail/{id}")
    public String detail(@PathVariable Long id, Model model, @AuthenticationPrincipal Member member) {
        Competition competition = competitionService.findOne(id);
        Member loginMember = member;
        model.addAttribute("competition", competition);
        model.addAttribute("loginMember", loginMember);

        return "competition/competition_detail";
    }

    @GetMapping("/competition/admin/register")
    public String registerForm() {
        return "competition/competition_register";
    }

    @PostMapping("/competition/admin/register")
    public String register(MultipartHttpServletRequest multiReq, @AuthenticationPrincipal Member member, Model model) throws IOException {
        String title = multiReq.getParameter("title");
        String host = multiReq.getParameter("host");
        String url = multiReq.getParameter("url");
        String content = multiReq.getParameter("content");
        String writer = member.getName();
        Long memberId = member.getId();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(multiReq.getParameter("startDate"),formatter);
        LocalDateTime endDate = LocalDateTime.parse(multiReq.getParameter("endDate"),formatter);

        Competition competition = new Competition();
        competition.setTitle(title);
        competition.setHost(host);
        competition.setUrl(url);
        competition.setContent(content);
        competition.setWriter(writer);
        competition.setStartDate(startDate);
        competition.setEndDate(endDate);
        competition.setMemberId(memberId
        );
        MultipartFile file = multiReq.getFile("file");

        competitionService.register(competition, file);

        Long id = competition.getId();
        model.addAttribute("id", id);
        return "redirect:/competition/competition_list";
    }

    @GetMapping("/competition/admin/delete/{id}")
    public String delete(@PathVariable Long id) {
        Competition competition = competitionService.findOne(id);
        competitionService.deleteOne(competition);

        return "redirect:/competition/competition_list";
    }

    @PostMapping("/competition/search")
    public String search(@RequestParam String keyword, Model model) {
        List<Competition> competitions = competitionService.findCompetitions(keyword);
        System.out.println("---------------search--------------");
        model.addAttribute("competitions", competitions);
        return "competition/competition_list";
    }

    @GetMapping("/competition/admin/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        Competition competition = competitionService.findOne(id);
        model.addAttribute("competition", competition);

        return "competition/competition_update";
    }

    @PostMapping("/competition/admin/update/{id}")
    public String update(@PathVariable Long id, MultipartHttpServletRequest multiReq, Member member) throws IOException {
        String title = multiReq.getParameter("title");
        String host = multiReq.getParameter("host");
        String url = multiReq.getParameter("url");
        String content = multiReq.getParameter("content");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(multiReq.getParameter("startDate"),formatter);
        LocalDateTime endDate = LocalDateTime.parse(multiReq.getParameter("endDate"),formatter);

        MultipartFile file = multiReq.getFile("file");

        Competition competition = competitionService.findOne(id);
        competition.setTitle(title);
        competition.setHost(host);
        competition.setUrl(url);
        competition.setContent(content);
        competition.setStartDate(startDate);
        competition.setEndDate(endDate);


        competitionService.update(competition, file);
        return "redirect:/competition/competition_list";

    }

    @GetMapping("/competition/admin/myCompetition")
    public String myCompetition(Model model, @AuthenticationPrincipal Member member) {
        Long memberId = member.getId();
        List<Competition> competitions = competitionService.findByLoginId(memberId);
        model.addAttribute("competitions", competitions);
        return "competition/competition_list";
    }
}
