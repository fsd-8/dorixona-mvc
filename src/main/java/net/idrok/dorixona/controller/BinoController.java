package net.idrok.dorixona.controller;

import net.idrok.dorixona.model.Bino;
import net.idrok.dorixona.service.BinoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pages")
public class BinoController {

    private final BinoService binoService;

    public BinoController(BinoService binoService) {
        this.binoService = binoService;
    }


    @GetMapping("/bino")
    public String binoPage(Model model){
        model.addAttribute("binolar", binoService.getAll());
        return "bino";
    }


}
