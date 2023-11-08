package com.Paamelding.PaameldingTilFest.web;

import com.Paamelding.PaameldingTilFest.Service.PaameldingService;
import com.Paamelding.PaameldingTilFest.Service.PassordService;
import com.Paamelding.PaameldingTilFest.model.Deltager;
import com.Paamelding.PaameldingTilFest.model.Passord;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;




@Controller
public class PaameldingController {
    @Autowired
    private PaameldingService paameldingService;
    @Autowired
    private PassordService passordService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Passord.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(paameldingService.hashedPassord(text));
            }
        });
    }

    @GetMapping("/")
    public String index() {
        return "index";  // this should match the name of your JSP file without the .jsp extension
    }
    @GetMapping("/paamelding")
    public String paameldingSkjema() {
        return "paamelding";
    }


    @PostMapping("/paamelding") //Henter info input fra paamelding.jsp
    public String handlePaamelding(
                                   @RequestParam String passord,
                                   RedirectAttributes redirectAttributes,
                                   @Valid @ModelAttribute Deltager deltager,
                                   BindingResult bindingResult) {
        String feilmeldinger;

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                if (error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                    System.out.println("Field error in field '" + fieldError.getField() + "': " + fieldError.getDefaultMessage());
                } else {
                    System.out.println("Object error: " + error.getDefaultMessage());
                }
            });

            feilmeldinger = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .reduce("", (f, e) -> f + e + "<br>");

            redirectAttributes.addFlashAttribute("feilmeldinger", feilmeldinger);

            return "redirect:/paamelding";
        } else if (paameldingService.existsByMobil(deltager.getMobil())) { //sjekker om det eksisterer allerede nummeret
            feilmeldinger = "mobil eksisterer allerede";
            redirectAttributes.addFlashAttribute("feilmeldinger", feilmeldinger);
            return "redirect:/paamelding";
        }


        Deltager deltagere = paameldingService.registerUser(
                deltager.getFornavn(),
                deltager.getEtternavn(),
                deltager.getMobil(),
                passord,
                deltager.getKjonn().name()
        );
        redirectAttributes.addFlashAttribute("deltager", deltagere);

        return "redirect:/paameldt";
    }

}
