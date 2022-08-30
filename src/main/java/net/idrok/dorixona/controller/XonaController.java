package net.idrok.dorixona.controller;

import net.idrok.dorixona.model.Xona;
import net.idrok.dorixona.service.BinoService;
import net.idrok.dorixona.service.XonaService;
import net.idrok.dorixona.service.impl.BinoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pages/xona")
public class XonaController {

    private final XonaService xonaService;
    private final BinoService binoService;

    public XonaController(XonaService xonaService, BinoService binoService) {
        this.xonaService = xonaService;
        this.binoService = binoService;
    }


    @GetMapping()
    public String xonaPage(Model model){
        model.addAttribute("binolar", binoService.getAll());
        model.addAttribute("xonalar", xonaService.getAll());
        model.addAttribute("xona", new Xona());
        return "xona";
    }
    @PostMapping("create")
    public String xonaCreate(@ModelAttribute("xona") Xona xona, Model model){
        xonaService.create(xona);
        return "redirect:/pages/xona";
    }
    @GetMapping("/{id}")
    public String xonaUpdateRequest(@PathVariable Long id, Model model){
        model.addAttribute("edit", true);
        model.addAttribute("xona", xonaService.getById(id));
        model.addAttribute("xonalar", xonaService.getAll());
        model.addAttribute("binolar", binoService.getAll());

        return "xona";
    }
    @PostMapping("update")
    public String XonaUpdate(@ModelAttribute("xona") Xona xona, Model model){
        xonaService.update(xona);
        return "redirect:/pages/xona";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        xonaService.delete(id);
        return "redirect:/pages/xona";
    }


}
