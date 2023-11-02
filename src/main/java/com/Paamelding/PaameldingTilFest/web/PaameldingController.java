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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

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
    public String handlePaamelding(
                                   @RequestParam String fornavn, @RequestParam String etternavn, @RequestParam String mobil,
                                   @RequestParam String passord, @RequestParam String repetertpassord, @RequestParam String kjonn,
                                   RedirectAttributes redirectAttributes) {

        if (paameldingService.existsById(mobil)) {
            throw new IllegalArgumentException("Mobile number already registered");
        }

        if(!Objects.equals(passord, repetertpassord)){
            redirectAttributes.addFlashAttribute("error", "Passord matcher ikke");
            return "redirect:/paamelding";
        }

        Deltager deltager = paameldingService.registerUser(fornavn,etternavn, mobil, passord, kjonn);
        redirectAttributes.addFlashAttribute("deltager", deltager);

        redirectAttributes.addFlashAttribute("message", "Registration successful!");

        return "redirect:/paameldt";
    }



}
