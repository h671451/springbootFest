package com.Paamelding.PaameldingTilFest.web;

import com.Paamelding.PaameldingTilFest.Service.PaameldingService;
import com.Paamelding.PaameldingTilFest.Service.PassordService;
import com.Paamelding.PaameldingTilFest.model.Deltager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class PaameldingController {
    @Autowired
    private PaameldingService paameldingService;
    @Autowired
    private PassordService passordService;

    @GetMapping("/")
    public String index() {
        return "index";  // this should match the name of your JSP file without the .jsp extension
    }
    @GetMapping("/paamelding")
    public String paameldingSkjema(Model model) {
        model.addAttribute("Deltager", new Deltager());
        return "paamelding";
    }

    @GetMapping("/innlogging")
    public String innloggingSkjema() {
        return "innlogging";
    }

    @GetMapping("/paameldt")
    public String paameldtSkjema() {
        return "paameldt";
    }


//endringer

    @PostMapping("/paamelding")
    public String handlePaamelding( @RequestParam String mobil,  @RequestParam String fornavn,  @RequestParam String etternavn,  @RequestParam String passord,  @RequestParam String repetertpassord,  @RequestParam String kjonn, RedirectAttributes redirectAttributes) {


        Deltager deltager = paameldingService.registerUser(mobil, fornavn,etternavn, passord, repetertpassord, kjonn);
        redirectAttributes.addFlashAttribute("deltager", deltager);

        redirectAttributes.addFlashAttribute("message", "Registration successful!");

        return "redirect:/paameldt";
    }

    @PostMapping("/innlogging")
    public String handleInnlogging(@RequestParam String mobil, @RequestParam String rawPassword,  RedirectAttributes redirectAttributes, BindingResult bindingResult) {

        if (paameldingService.authenticateUser(mobil, rawPassword)) {
            return "redirect:/deltagerliste";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid mobile number or password.");
            return "redirect:/innlogging";
        }
    }


    @GetMapping("/deltagerliste")
    public String deltagerlisteSkjema(Model model) {
        List<Deltager> deltagerList = paameldingService.finnAlleDeltager();
        model.addAttribute("deltagers", deltagerList);
        return "deltagerliste";
    }


}
