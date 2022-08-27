package net.idrok.dorixona.config;


import net.idrok.dorixona.model.Bino;
import net.idrok.dorixona.model.Xona;
import net.idrok.dorixona.service.BinoService;
import net.idrok.dorixona.service.XonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbInit {

    @Autowired
    BinoService binoService;

    @Autowired
    XonaService xonaService;

    @PostConstruct
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
     @PostConstruct
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
