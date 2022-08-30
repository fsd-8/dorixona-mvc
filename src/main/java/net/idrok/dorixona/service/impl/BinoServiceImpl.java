package net.idrok.dorixona.service.impl;

import net.idrok.dorixona.model.Bino;
import net.idrok.dorixona.repository.BinoRepository;
import net.idrok.dorixona.service.BinoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinoServiceImpl implements BinoService {

    private final BinoRepository binoRepository;

    public BinoServiceImpl(BinoRepository binoRepository) {
        this.binoRepository = binoRepository;
    }



    public List<Bino> getAll(){
        return binoRepository.findAll();
    }

    public Page<Bino> getAll(String query, Pageable pageable){
        return binoRepository.findAllByQuery(query, pageable);
    }

    public Bino create(Bino bino) {
        return binoRepository.save(bino);
    }
    public void delete(Long id) {
        binoRepository.deleteById(id);
    }


    public Bino getById(Long id) {
        return binoRepository.findById(id).orElseThrow(()->new RuntimeException("Xatolik"));
    }


    public Bino update(Bino bino) {
        return binoRepository.save(bino);
    }
}
