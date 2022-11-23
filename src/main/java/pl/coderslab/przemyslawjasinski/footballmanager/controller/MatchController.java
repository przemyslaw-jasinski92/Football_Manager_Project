package pl.coderslab.przemyslawjasinski.footballmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Match;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Round;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Team;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.MatchRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.RoundRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.TeamRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/matches")
public class MatchController {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    private final RoundRepository roundRepository;


    @GetMapping("/setresult/{matchId}")
    public String setMatch(@PathVariable Long matchId, HttpServletRequest request, Model model) {
        Match match = matchRepository.getMatchById(matchId);
        if (match.getHomeTeam().getStadium() == null) {
            model.addAttribute("messageError", "Drużyna gospodarzy nie ma przypisanego stadionu.");
        }
        if (match != null) {
            model.addAttribute("setMatch", match);
            return "setMatchResult";
        }
        return "redirect:/rounds";
    }

    @PostMapping("/setresult/{matchId}")
    public String saveMatch(@Valid @ModelAttribute(name = "setMatch") Match match, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "setMatchResult";
        }
        Team homeTeam = teamRepository.findTeamById(match.getHomeTeam().getId());
        Team awayTeam = teamRepository.findTeamById(match.getAwayTeam().getId());
        if (match.getHomeTeamGoals().equals(match.getAwayTeamGoals())) {
            homeTeam.setDraws(homeTeam.getDraws() + 1);
            homeTeam.setPoints(homeTeam.getPoints() + 1);
            awayTeam.setDraws(awayTeam.getDraws() + 1);
            awayTeam.setPoints(awayTeam.getPoints() + 1);
        } else if (match.getHomeTeamGoals() > match.getAwayTeamGoals()) {
            homeTeam.setWins(homeTeam.getWins() + 1);
            homeTeam.setPoints(homeTeam.getPoints() + 3);
            awayTeam.setLost(awayTeam.getLost() + 1);
        } else {
            awayTeam.setWins(awayTeam.getWins() + 1);
            awayTeam.setPoints(awayTeam.getPoints() + 3);
            homeTeam.setLost(homeTeam.getLost() + 1);
        }
        homeTeam.setAllScoresGoals(homeTeam.getAllScoresGoals() + match.getHomeTeamGoals());
        homeTeam.setAllLostGoals(homeTeam.getAllLostGoals() + match.getAwayTeamGoals());
        awayTeam.setAllScoresGoals(awayTeam.getAllScoresGoals() + match.getAwayTeamGoals());
        awayTeam.setAllLostGoals(awayTeam.getAllLostGoals() + match.getHomeTeamGoals());
        matchRepository.save(match);
        model.addAttribute("message", "Mecz został zakończony.");
        model.addAttribute("rounds", roundRepository.findAll());
        return "redirect:/rounds?msg=" + URLEncoder.encode("Zakończono mecz.", StandardCharsets.UTF_8);
    }

    @GetMapping("/team/{teamId}")
    public String teamMatches(@PathVariable Long teamId, Model model) {
        Team team = teamRepository.findTeamById(teamId);
        if (team != null) {
            model.addAttribute("matches", matchRepository.findAllMatchesByTeamId(teamId));
            return "matches";
        }
        return "redirect:/teams";
    }

    @GetMapping("/matchdetails/{matchId}")
    public String matchDetails(@PathVariable Long matchId, Model model) {
        model.addAttribute("matchDetails", matchRepository.getMatchById(matchId));
        return "/matchDetails";
    }

    @ModelAttribute("globalRounds")
    public List<Round> rounds() {
        return roundRepository.findAll();
    }
}
