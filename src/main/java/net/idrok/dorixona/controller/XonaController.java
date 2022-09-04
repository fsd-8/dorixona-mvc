package net.idrok.dorixona.controller;

import net.idrok.dorixona.model.Xona;
import net.idrok.dorixona.service.BinoService;
import net.idrok.dorixona.service.XonaService;
import net.idrok.dorixona.service.impl.BinoServiceImpl;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

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
        return "xona";
    }
    @GetMapping("/rasm/{id}")
    public void downloadImage(@PathVariable Long id, HttpServletResponse res) throws IOException {
        Xona xona = xonaService.getById(id);
        byte[] rasm = xona.getRasm();
        res.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM.getType());
        res.setHeader("Content-Disposition", "attachment; filename=" + xona.getNom()+"_rasm.jpg");
        res.setContentLength(rasm.length);

        OutputStream os = res.getOutputStream();
        try {
            os.write(rasm, 0, rasm.length);
        } finally {
            os.close();
        }


    }
    @PostMapping("create")
    public String xonaCreate(@ModelAttribute("xona") Xona xona, @RequestParam("rasmcha") MultipartFile rasm, Model model) throws IOException {
       if(rasm != null) xona.setRasm(rasm.getBytes());
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
