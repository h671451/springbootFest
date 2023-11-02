package com.Paamelding.PaameldingTilFest.web;

import com.Paamelding.PaameldingTilFest.Service.PaameldingService;
import com.Paamelding.PaameldingTilFest.Service.PassordService;
import com.Paamelding.PaameldingTilFest.model.Deltager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller

public class DeltagerListeController {
    @Autowired
    private PaameldingService paameldingService;
    @Autowired
    private PassordService passordService;
    @GetMapping("/deltagerliste")
    public String deltagerlisteSkjema(Model model, HttpServletRequest request) {
        List<Deltager> deltagerList = paameldingService.finnAlleDeltager();
        model.addAttribute("deltagers", deltagerList);

        // Assuming you save mobile as "userMobil" in session during login.
        String userMobil = (String) request.getSession().getAttribute("userMobil");
        if (userMobil != null) {
            Deltager loggedInDeltager = paameldingService.findByMobil(userMobil);
            model.addAttribute("loggedInDeltager", loggedInDeltager);

        }

        return "deltagerliste";
    }

}
