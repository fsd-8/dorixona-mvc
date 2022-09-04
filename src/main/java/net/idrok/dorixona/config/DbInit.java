package net.idrok.dorixona.config;


import net.idrok.dorixona.model.Bino;
import net.idrok.dorixona.model.Lavozim;
import net.idrok.dorixona.model.User;
import net.idrok.dorixona.model.Xona;
import net.idrok.dorixona.repository.UserRepository;
import net.idrok.dorixona.service.XonaService;
import net.idrok.dorixona.service.impl.BinoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbInit {

    @Autowired
    BinoServiceImpl binoService;

    @Autowired
    XonaService xonaService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostConstruct
    public void userInit(){
        // First admin
        if(userRepository.findByLogin("admin123").isEmpty()){
            User user = new User();
            user.setIsm("Admin");
            user.setFamiliya("admin");
            user.setLavozim(Lavozim.ADMIN);
            user.setLogin("admin123");
            user.setParol(encoder.encode("admin123"));
            user.setTelefon("1234");
            user.setAktive(true);
            userRepository.save(user);
        }

        // First user
        if(userRepository.findByLogin("user123").isEmpty()){
            User user = new User();
            user.setIsm("User");
            user.setFamiliya("User");
            user.setLavozim(Lavozim.USER);
            user.setLogin("user123");
            user.setParol(encoder.encode("user123"));
            user.setTelefon("1234");
            user.setAktive(true);
            userRepository.save(user);
        }


    }

//    @PostConstruct
    public void fillBino(){
        for (int i = 1; i <= 500; i++) {
            Bino bino = new Bino();
            bino.setNom("bino "+i);
            bino.setInfo("info " + i);
            try{
            binoService.create(bino);

            } catch (Exception ex){

            }
        }
    }
//     @PostConstruct
    public void fillXona(){
        for (int i = 1; i <= 500; i++) {
            Bino bino = new Bino();
            bino.setId(i % 20L+1L);

            Xona xona = new Xona();
            xona.setNom("xona "+i);
            xona.setBino(bino);
            xona.setInfo("info "+i);
        try{
                    xonaService.create(xona);
        } catch (Exception ex){

        }
        }
    }


}
