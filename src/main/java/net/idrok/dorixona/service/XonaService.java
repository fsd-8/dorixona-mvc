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

    public boolean create(Xona xona) {
        return xonaRepository.create(xona);
    }
    public boolean delete(Long id) {
        return xonaRepository.deleteById(id);
    }


    public Xona getById(Long id) {

        return xonaRepository.findById(id);
    }


    public boolean update(Xona xona) {
        return xonaRepository.update(xona);
    }
}
