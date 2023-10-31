package com.Paamelding.PaameldingTilFest.web;

import com.Paamelding.PaameldingTilFest.Service.PaameldingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class paameldingController {
    //@Autowired private PaameldingService paameldingService;
    @GetMapping("/")
    public String index() {
        return "index";  // this should match the name of your JSP file without the .jsp extension
    }
    @GetMapping("/paamelding")
    public String paameldingSkjema() {
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

    @GetMapping("/deltagerliste")
    public String deltagerlisteSkjema() {
        return "deltagerliste";
    }




}
