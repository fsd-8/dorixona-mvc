package net.idrok.dorixona.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.net.http.HttpHeaders;

@Controller
public class HomeController {
    
    @GetMapping()
    public String homePage(@RequestHeader HttpHeaders httpHeaders, Model model){
        // o'qish
        String ism = httpHeaders.firstValue("ism").orElse("Ism yo'q");
        model.addAttribute("ism", ism);
        return "index";
    }

//    @GetMapping()
//    public String homePage(@RequestHeader("ism") String ism, Model model){
//        // o'qish
//        if(ism != null){
//            model.addAttribute("ism", ism);
//        }
//        return "index";
//    }


//    @GetMapping()
//    public String homePage(HttpServletRequest req, HttpServletResponse res, Model model){
//        // o'qish
//        String ism = req.getHeader("ism");
//        if(ism != null){
//            model.addAttribute("ism", ism);
//        }
//        // yozish
//        res.setHeader("familiya", "Qayum");
//        return "index";
//    }


    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
        
        ModelAndView errorPage = new ModelAndView("error");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
        }
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }
    
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }
}
