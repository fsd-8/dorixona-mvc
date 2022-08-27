package net.idrok.dorixona.service;

import net.idrok.dorixona.model.Xona;
import net.idrok.dorixona.repository.XonaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class XonaService {

    private final XonaRepository xonaRepository;

    public XonaService(XonaRepository xonaRepository) {
        this.xonaRepository = xonaRepository;
    }


    public List<Xona> getAll(){
       return xonaRepository.findAll();
    }

    public Xona create(Xona xona) {
        return xonaRepository.save(xona);
    }
    public void delete(Long id) {
         xonaRepository.deleteById(id);
    }


    public Xona getById(Long id) {

        return xonaRepository.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }


    public Xona update(Xona xona) {
        return xonaRepository.save(xona);
    }
}
