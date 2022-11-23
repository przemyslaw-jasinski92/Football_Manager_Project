package pl.coderslab.przemyslawjasinski.footballmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Round;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Stadium;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Team;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.RoundRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.StadiumRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.TeamRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {
    private final TeamRepository teamRepository;
    private final RoundRepository roundRepository;
    private final StadiumRepository stadiumRepository;

//    @GetMapping("/create")
//    @ResponseBody
//    public String createTeams() {
//        Team team1 = new Team();
//        team1.setName("Legia Warszawa");
//        team1.setStadium("Warsaw, ul. Łazienkowska 3");
//        teamRepository.save(team1);
//
//        Team team2 = new Team();
//        team2.setName("Cracovia");
//        team2.setStadium("Kraków, ul. Kałuży 1");
//        teamRepository.save(team2);
//
//        Team team3 = new Team();
//        team3.setName("Piast Gliwice");
//        team3.setStadium("Gliwice, ul. Okrzei 20");
//        teamRepository.save(team3);
//
//        return "save to base";
//    }

    @GetMapping("")
    public String findAll(Model model) {
        List<Round> rounds = roundRepository.findAll();
        if (rounds.isEmpty()) {
            model.addAttribute("messageError", "Brak terminarza ligi. Wygeneruj kolejki.");
        }
        List<Team> teams = teamRepository.findAllByOrderByPointsDesc();
        model.addAttribute("teams", teams);
        return "teams";
    }

    @GetMapping("/list")
    public String editTeams(HttpServletRequest request, Model model) {
        String msg = request.getParameter("msg");
        if (msg != null) {
            model.addAttribute("message", msg);
        }
        model.addAttribute("teamsList", teamRepository.findAllByOrderByNameAsc());
        return "editTeams";
    }

    @GetMapping("/edit/{teamId}")
    public String editTeam(@PathVariable Long teamId, Model model) {
        model.addAttribute("editTeam", teamRepository.findTeamById(teamId));
        return "team-form";
    }

    @PostMapping("/edit/{teamId}")
    public String editTeamSubmit(@Valid @ModelAttribute(name = "editTeam") Team team, BindingResult result) {
        if (result.hasErrors()) {
            return "team-form";
        }
        teamRepository.save(team);
        return "redirect:/teams/list?msg=" + URLEncoder.encode("Zaktualizowano drużynę", StandardCharsets.UTF_8);
    }

    @ModelAttribute("stadiums")
    public List<Stadium> stadiums() {
        return stadiumRepository.findAll();
    }
}
