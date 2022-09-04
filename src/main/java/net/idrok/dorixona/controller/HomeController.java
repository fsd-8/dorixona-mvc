package net.idrok.dorixona.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.idrok.dorixona.model.Lavozim;
import net.idrok.dorixona.model.User;
import net.idrok.dorixona.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.HttpCookie;
import java.net.http.HttpHeaders;
import java.time.Instant;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping()
    public String homePage(HttpServletRequest req, HttpServletResponse res, Model model){

        return "index";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest req, HttpServletResponse res, Model model){
        return "login";
    }
  @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, Model model){
        user.setAktive(true);
        user.setLavozim(Lavozim.USER);
        userRepository.save(user);
        return "redirect:login";
    }
   @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String accessDenied(HttpServletRequest req, HttpServletResponse res, Model model){
        return "access-denied";
    }



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
