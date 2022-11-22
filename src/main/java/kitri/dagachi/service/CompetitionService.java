package kitri.dagachi.service;

import kitri.dagachi.model.Competition;
import kitri.dagachi.repository.CompetitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

}
