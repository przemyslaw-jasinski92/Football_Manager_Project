package pl.coderslab.przemyslawjasinski.footballmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Team;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Winners;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.MatchRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.RoundRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.TeamRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.WinnersRepository;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/winners")
public class WinnersController {
    private final WinnersRepository winnersRepository;
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;
    private final RoundRepository roundRepository;

    @GetMapping("/finishseason")
    public String endSeason() {
        Team winningTeam = teamRepository.findFirstByOrderByPointsDesc();
        Winners winner = new Winners();
        winner.setTeamName(winningTeam.getName());
        winner.setAllScoreGoals(winningTeam.getAllScoresGoals());
        winner.setAllLostGoals(winningTeam.getAllLostGoals());
        winner.setPoints(winningTeam.getPoints());
        winnersRepository.save(winner);
        matchRepository.deleteAll();
        roundRepository.deleteAll();
        List<Team> teams = teamRepository.findAll();
        for (Team team : teams) {
            team.reset();
            teamRepository.save(team);
        }
        return "redirect:/schedulemanager?msg=" + URLEncoder.encode("Rozgrywki zostały poprawnie zakończone.", StandardCharsets.UTF_8);
    }

    @GetMapping("")
    public String winnersList(Model model) {
        List<Winners> winners = winnersRepository.findWinnersByOrderByCreatedAtDesc();
        if(winners.isEmpty()){
            return "redirect:/rounds";
        }
        model.addAttribute("winners", winners);
        return "winnersList";
    }
}
