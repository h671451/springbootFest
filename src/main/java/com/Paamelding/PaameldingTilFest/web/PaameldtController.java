package com.Paamelding.PaameldingTilFest.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller

public class PaameldtController {

    @GetMapping("/paameldt")
    public String paameldtSkjema() {
        return "paameldt";
    }



}
