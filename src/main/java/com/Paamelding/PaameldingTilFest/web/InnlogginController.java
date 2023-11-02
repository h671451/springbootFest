package com.Paamelding.PaameldingTilFest.web;

import com.Paamelding.PaameldingTilFest.Service.PaameldingService;
import com.Paamelding.PaameldingTilFest.Service.PassordService;
import com.Paamelding.PaameldingTilFest.model.Deltager;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller

public class InnlogginController {
    @Autowired
    private PaameldingService paameldingService;
    @Autowired
    private PassordService passordService;
    @GetMapping("/innlogging")
    public String innloggingSkjema(Model model) {
        model.addAttribute("Deltager", new Deltager());
        return "innlogging";
    }

    @PostMapping("/innlogging")
    public String handleInnlogging(@RequestParam String mobil, @RequestParam String passord, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        if (paameldingService.authenticateUser(mobil, passord)) {
            request.getSession().setAttribute("brukerNummer", mobil); // Save the mobile number in the session
            return "redirect:/deltagerliste";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid mobile number or password.");
            return "redirect:/innlogging";
        }
    }



}
