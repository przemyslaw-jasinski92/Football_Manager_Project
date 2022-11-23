package pl.coderslab.przemyslawjasinski.footballmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Stadium;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Team;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.StadiumRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.TeamRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("stadiums")
public class StadiumController {
    private final StadiumRepository stadiumRepository;
    private final TeamRepository teamRepository;

    @GetMapping("/add")
    public String addStadium(Model model) {
        model.addAttribute("stadium", new Stadium());
        return "stadium-form";
    }

    @PostMapping("/add")
    public String addStadiumSubmit(@Valid @ModelAttribute(name = "stadium") Stadium stadium, BindingResult result) {
        if (result.hasErrors()) {
            return "stadium-form";
        }
        stadiumRepository.save(stadium);
        return "redirect:/stadiums?msg=" + URLEncoder.encode("Dodano nowy stadion", StandardCharsets.UTF_8);
    }

    @GetMapping("/edit/{stadiumId}")
    public String editStadium(@PathVariable Long stadiumId, Model model) {
        Stadium stadium = stadiumRepository.findStadiumById(stadiumId);
        if (stadium == null) {
            return "redirect:/stadiums";
        }
        model.addAttribute("stadium", stadium);
        return "stadium-form";
    }

    @PostMapping("/edit/{stadiumId}")
    public String editStadiumSubmit(@Valid @ModelAttribute(name = "stadium") Stadium stadium, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "stadium-form";
        }
        stadiumRepository.save(stadium);
        return "redirect:/stadiums?msg=" + URLEncoder.encode("Zmiany zostałe zapisane", StandardCharsets.UTF_8);
    }

    @GetMapping("")
    public String stadiumList(Model model, HttpServletRequest request) {
        String msg = request.getParameter("msg");
        if (msg != null) {
            model.addAttribute("message", msg);
        }
        model.addAttribute("stadiums", stadiumRepository.findAllByOrderByCityAsc());
        return "stadiumsList";
    }

    @GetMapping("/delete/{stadiumId}")
    public String deleteStadium(@PathVariable Long stadiumId) {
        List<Team> teams = teamRepository.findTeamsByStadiumId(stadiumId);
        for (Team team : teams) {
            team.setStadium(null);
        }
        stadiumRepository.deleteById(stadiumId);
        return "redirect:/stadiums?msg=" + URLEncoder.encode("Stadion został usunięty", StandardCharsets.UTF_8);
    }
}
