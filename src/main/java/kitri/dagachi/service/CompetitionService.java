package kitri.dagachi.service;

import kitri.dagachi.model.Competition;
import kitri.dagachi.repository.CompetitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
@Service
@RequiredArgsConstructor
@Transactional
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    public List<Competition> findAllCompetition() {
        return competitionRepository.findAll();
    }

    public Competition findOne(Long id) {
        return competitionRepository.findById(id);
    }

    public List<Competition> findByLoginId(Long memberId) {
        return competitionRepository.findByMid(memberId);
    }
    private String fileDir="D:/test/poster/";

    public void register(Competition competition, MultipartFile file) throws IOException {

        String orgName = file.getOriginalFilename();
        String uuid = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss_")).toString();
        String savedName = uuid + orgName;
        String savedPath = fileDir + savedName;

        competition.setOrgName(orgName);
        competition.setSavedName(savedName);
        competition.setSavedPath(savedPath);

        file.transferTo(new File(savedPath));

        competitionRepository.save(competition);
    }

    public void update(Competition competition, MultipartFile file) throws IOException{

        String orgName = file.getOriginalFilename();
        String uuid = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmss_")).toString();
        String savedName = uuid + orgName;
        String savedPath = fileDir + savedName;

        competition.setOrgName(orgName);
        competition.setSavedName(savedName);
        competition.setSavedPath(savedPath);

        file.transferTo(new File(savedPath));
    }

    public void deleteOne(Competition competition) {
        competitionRepository.delete(competition);
    }

    public List<Competition> findCompetitions(String keyword) {
        return competitionRepository.findByKeyword(keyword);
    }
}
