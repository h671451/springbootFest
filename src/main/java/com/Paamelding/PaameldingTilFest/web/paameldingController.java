package com.Paamelding.PaameldingTilFest.web;

import com.Paamelding.PaameldingTilFest.Service.PaameldingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class paameldingController {
    //@Autowired private PaameldingService paameldingService;

    @RequestMapping("/paamelding")
    public String paameldingSkjema() {
        return "paamelding";
    }




}
