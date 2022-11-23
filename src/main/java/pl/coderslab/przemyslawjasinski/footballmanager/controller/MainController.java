package pl.coderslab.przemyslawjasinski.footballmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.przemyslawjasinski.footballmanager.model.Round;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.MatchRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.RoundRepository;
import pl.coderslab.przemyslawjasinski.footballmanager.repository.TeamRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class MainController {

    private final TeamRepository teamRepository;
    private final RoundRepository roundRepository;

    private final MatchRepository matchRepository;

    @GetMapping("")
    public String main(HttpServletRequest request, Model model) {
        String msg = request.getParameter("msg");
        if (msg != null) {
            model.addAttribute("message", msg);
        }
        List<Round> rounds = roundRepository.findAll();
        if (rounds.isEmpty()) {
            model.addAttribute("messageError", "Brak terminarza ligi. Wygeneruj kolejki.");
            return "main";
        }

        if (matchRepository.countMatchesByUpdatedAtIsNotNull() == matchRepository.count()) {
            model.addAttribute("winner", teamRepository.findFirstByOrderByPointsDesc());
        } else {
            model.addAttribute("matchesToEnd", matchRepository.countMatchesByUpdatedAtIsNull());
            model.addAttribute("topThree", teamRepository.findFirst3ByOrderByPointsDesc());
        }
        return "main";
    }

    @GetMapping("/schedulemanager")
    public String scheduleManager(HttpServletRequest request, Model model) {
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
            model.addAttribute("schedule", "empty");
        }
        if (matchRepository.countMatchesByUpdatedAtIsNotNull() == matchRepository.count()) {
            model.addAttribute("endSeason", true);
        }
        return "scheduleManager";
    }
}
