package net.idrok.dorixona.controller;

import net.idrok.dorixona.model.Bino;
import net.idrok.dorixona.service.BinoService;
import net.idrok.dorixona.service.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/pages/bino")
public class BinoController {

    private final BinoService binoService;
    private final FileService fileService;

    public BinoController(BinoService binoService, FileService fileService) {
        this.binoService = binoService;
        this.fileService = fileService;
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
    public String binoCreate(@ModelAttribute("bino") Bino bino, @RequestParam("file") MultipartFile multipartFile, Model model){
        String nom = fileService.saveFile(multipartFile);
        bino.setRasm(nom);
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