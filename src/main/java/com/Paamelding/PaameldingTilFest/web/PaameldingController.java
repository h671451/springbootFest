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


    @PostMapping("/paamelding")
    public String handlePaamelding( @RequestParam String fornavn,  @RequestParam String etternavn, @RequestParam String mobil, @RequestParam String passord,  @RequestParam String repetertpassord,  @RequestParam String kjonn, RedirectAttributes redirectAttributes) {

        Deltager deltager = paameldingService.registerUser(fornavn,etternavn, mobil, passord, repetertpassord, kjonn);
        redirectAttributes.addFlashAttribute("deltager", deltager);

        redirectAttributes.addFlashAttribute("message", "Registration successful!");

        return "redirect:/paameldt";
    }



}
