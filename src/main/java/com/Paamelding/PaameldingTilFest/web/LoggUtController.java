package com.Paamelding.PaameldingTilFest.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoggUtController {


    @PostMapping("/logout")
    public String handleLogout(HttpServletRequest request , RedirectAttributes redirectAttributes) {
        // Invalidate the session (if you're using sessions)
        request.getSession().invalidate();

        // Add a loggedOut flash attribute
        redirectAttributes.addFlashAttribute("loggedOut", true);

        // Redirect back to the login page
        return "redirect:/innlogging";
    }
}
