package net.idrok.dorixona.controller;

import net.idrok.dorixona.model.Bino;
import net.idrok.dorixona.service.BinoService;
import net.idrok.dorixona.service.impl.BinoServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pages/bino")
public class BinoController {

    private final BinoService binoService;

    public BinoController(BinoService binoService) {
        this.binoService = binoService;
    }


    @GetMapping()
    public String binoPage(@RequestParam(name = "qidiruv", required = false) String query, Pageable pageable, Model model){
        if(query == null) query = "";
        Page<Bino> page = binoService.getAll(query, pageable);
        model.addAttribute("binoPage", page);
        model.addAttribute("bino", new Bino());
        return "bino";
    }


    @PostMapping("create")
    public String binoCreate(@ModelAttribute("bino") Bino bino, Model model){
        binoService.create(bino);
        return "redirect:/pages/bino";
    }
    @GetMapping("/{id}")
    public String binoUpdateRequest(@PathVariable Long id, Model model){
        model.addAttribute("edit", true);
        model.addAttribute("bino", binoService.getById(id));
        model.addAttribute("binolar", binoService.getAll());

        return "bino";
    }
    @PostMapping("update")
    public String binoUpdate(@ModelAttribute("bino") Bino bino, Model model){
        binoService.update(bino);
        return "redirect:/pages/bino";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        binoService.delete(id);
        return "redirect:/pages/bino";
    }

}