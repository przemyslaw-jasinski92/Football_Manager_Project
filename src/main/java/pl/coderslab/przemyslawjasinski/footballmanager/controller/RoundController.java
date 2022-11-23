package pl.coderslab.przemyslawjasinski.footballmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Match;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Round;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Team;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.MatchRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.RoundRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.TeamRepository;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rounds")
public class RoundController {

    private final TeamRepository teamRepository;
    private final RoundRepository roundRepository;

    private final MatchRepository matchRepository;

    @GetMapping("/createrounds")
    public String createRounds() {
        List<Team> teamWithoutStadium = teamRepository.findTeamsByStadiumIsNull();
        if (teamWithoutStadium.size() > 0) {
            return "redirect:/schedulemanager?msgErr=" + URLEncoder.encode("Nie można utworzyć terminarza ligi, ponieważ " + teamWithoutStadium.size() + " drużyna/drużyny nie ma przypisanego stadionu.", StandardCharsets.UTF_8);
        }

        for (int i = 1; i <= teamRepository.count() * 2 - 2; i++) {
            Round round = new Round();
            round.setRoundNumber(i);
            roundRepository.save(round);
        }

        List<Team> allTeams = teamRepository.findAll();
        for (Team team : allTeams) {
            team.reset();
        }

//        if (allTeams.size() % 2 != 0) {
//            allTeams.add("Bye"); // If odd number of teams add a dummy
//        }

        int numDays = (allTeams.size() * 2 - 2); // Days needed to complete tournament
        int halfSize = allTeams.size() / 2;

        List<Team> teams = new ArrayList<>();

        teams.addAll(allTeams); // Add teams to List and remove the first team
        teams.remove(0);

        int teamsSize = teams.size();

        for (int day = 0; day < numDays; day++) {

            int teamIdx = day % teamsSize;
            Round round = roundRepository.findRoundByRoundNumber(day + 1);
            Match match = new Match();

            if (day < allTeams.size() - 1) {
                match.setHomeTeam(teams.get(teamIdx));
                match.setAwayTeam(allTeams.get(0));
                match.setRound(round);

            } else {
                match.setHomeTeam(allTeams.get(0));
                match.setAwayTeam(teams.get(teamIdx));
                match.setRound(round);

            }
            matchRepository.save(match);
            round.setMatches(matchRepository.findAllMatchesByRound(round.getId()));
            roundRepository.save(round);

            for (int idx = 1; idx < halfSize; idx++) {
                int firstTeam = (day + idx) % teamsSize;
                int secondTeam = (day + teamsSize - idx) % teamsSize;
                Match match2 = new Match();
                if (day < allTeams.size() - 1) {
                    match2.setRound(round);
                    match2.setHomeTeam(teams.get(firstTeam));
                    match2.setAwayTeam(teams.get(secondTeam));
                } else {
                    match2.setRound(round);
                    match2.setHomeTeam(teams.get(secondTeam));
                    match2.setAwayTeam(teams.get(firstTeam));
                }
                matchRepository.save(match2);
                round.setMatches(matchRepository.findAllMatchesByRound(round.getId()));
                roundRepository.save(round);
            }
        }
        return "redirect:/schedulemanager?msg=" + URLEncoder.encode("Utworzono terminarz spotkań.", StandardCharsets.UTF_8);
    }

    @GetMapping("")
    public String showRounds(Model model, HttpServletRequest request) {
        String msg = request.getParameter("msg");
        if (msg != null) {
            model.addAttribute("message", msg);
        }
        String msgErr = request.getParameter("msgErr");
        if (msgErr != null) {
            model.addAttribute("messageError", msgErr);
        }
        List<Round> rounds = roundRepository.findAll();
        if (rounds.isEmpty()) {
            model.addAttribute("messageError", "Brak terminarza ligi. Wygeneruj kolejki.");
        }
        model.addAttribute("rounds", roundRepository.findAll());
        return "rounds";
    }

    @PostMapping("")
    public String chooseRound(HttpServletRequest request, Model model) {
        model.addAttribute("rounds", "");
        String roundNumberValue = request.getParameter("round_number");
        try {
            int roundNumber = Integer.parseInt(roundNumberValue);
            model.addAttribute("round", roundRepository.findRoundByRoundNumber(roundNumber));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "rounds";
    }

    @GetMapping("/delete")
    public String deleteMatches(Model model) {
        matchRepository.deleteAll();
        roundRepository.deleteAll();
        List<Team> teams = teamRepository.findAll();
        for (Team team : teams) {
            team.reset();
            teamRepository.save(team);
        }
        return "redirect:/schedulemanager?msg=" + URLEncoder.encode("Skasowano terminarz spotkań.", StandardCharsets.UTF_8);
    }

    @ModelAttribute("globalRounds")
    public List<Round> rounds() {
        return roundRepository.findAll();
    }
}
