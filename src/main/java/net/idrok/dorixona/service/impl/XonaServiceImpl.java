package net.idrok.dorixona.service.impl;

import net.idrok.dorixona.model.Xona;
import net.idrok.dorixona.repository.XonaRepository;
import net.idrok.dorixona.service.XonaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XonaServiceImpl implements XonaService {
    private final XonaRepository xonaRepository;

    public XonaServiceImpl(XonaRepository xonaRepository) {
        this.xonaRepository = xonaRepository;
    }


    @Override
    public List<Xona> getAll() {
        return xonaRepository.findAll();
    }


    @Override
    public Xona getById(Long id) {
        return xonaRepository.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public Xona create(Xona xona) {
        return xonaRepository.save(xona);
    }

    @Override
    public Xona update(Xona xona) {
        return xonaRepository.save(xona);
    }

    @Override
    public void delete(Long id) {
        xonaRepository.deleteById(id);
    }
}
