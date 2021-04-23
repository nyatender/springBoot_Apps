package io.springforjames.coronavirustracker.controllers;

import io.springforjames.coronavirustracker.models.locationStates;
import io.springforjames.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("locationStates", coronaService.getStates());
        return "home";
    }

}
