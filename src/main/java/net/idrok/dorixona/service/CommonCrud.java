package net.idrok.dorixona.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommonCrud<E> {

    /**
     * Bu metod barcha entitylar ro'yxatini qaytaradi
     * @return List<E> ro'yxat
     */
    public List<E> getAll();


    /**
     * Bu metod berilgan id bo'yicha entityni topip qaytaradi
     * @return topilgan entity
     * @exception RuntimeException agar entity topilmasa
     */
    public E getById(Long id);

    /**
     * Bu metod yangi entity yaratidi.
     * @exception RuntimeException agar entityda id bo'lsa yoki boshqa maydonlari kam bo'lasa
     * @param entity
     * @return yangi entity
     */
    public E create(E entity);


    /**
     * Bu metod entity o'zgartiradi
     * @exception RuntimeException agar entityda id bo'lmasa yoki boshqa maydonlari kam bo'lasa
     * @param entity
     * @return o'zgartirilgan entity
     */
    public E update(E entity);


    /**
     * entityni berilgan id bo'yicha o'chirish
     * @param id
     */
    public void delete(Long id);

}
