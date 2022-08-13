package net.idrok.dorixona.controller;

import net.idrok.dorixona.model.Bino;
import net.idrok.dorixona.service.BinoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pages/xona")
public class XonaController {

    private final BinoService binoService;

    public XonaController(BinoService binoService) {
        this.binoService = binoService;
    }


    @GetMapping()
    public String binoPage(Model model){
        model.addAttribute("binolar", binoService.getAll());
        model.addAttribute("bino", new Bino());
        return "xona";
    }
    @PostMapping("create")
    public String binoCreate(@ModelAttribute("bino") Bino bino, Model model){
        binoService.create(bino);
        return "redirect:/pages/xona";
    }
    @GetMapping("/{id}")
    public String binoUpdateRequest(@PathVariable Long id, Model model){
        model.addAttribute("edit", true);
        model.addAttribute("bino", binoService.getById(id));
        model.addAttribute("binolar", binoService.getAll());

        return "xona";
    }
    @PostMapping("update")
    public String binoUpdate(@ModelAttribute("bino") Bino bino, Model model){
        binoService.update(bino);
        return "redirect:/pages/xona";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        binoService.delete(id);
        return "redirect:/pages/xona";
    }


}
