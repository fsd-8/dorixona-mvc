package net.idrok.dorixona.service;

import net.idrok.dorixona.model.Bino;
import net.idrok.dorixona.repository.BinoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BinoService {

    private final BinoRepository binoRepository;

    public BinoService(BinoRepository binoRepository) {
        this.binoRepository = binoRepository;
    }


    public List<Bino> getAll(){
       return binoRepository.findAll();
    }
}
