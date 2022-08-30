package net.idrok.dorixona.service;

import net.idrok.dorixona.model.Bino;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BinoService extends CommonCrud<Bino>{
    /**
     * Berilgan query bo'yicha filterlaydi va pageable paramter bo'yicha sahifalaydi, saralaydi
     * @param query
     * @param pageable
     * @return page
     */
    public Page<Bino> getAll(String query, Pageable pageable);

}
