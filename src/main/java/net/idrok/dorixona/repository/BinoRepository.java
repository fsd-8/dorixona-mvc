package net.idrok.dorixona.repository;

import net.idrok.dorixona.model.Bino;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BinoRepository extends JpaRepository<Bino, Long> {

    @Query("FROM Bino b WHERE UPPER(b.nom) like CONCAT('%', UPPER(:query), '%') or UPPER(b.info) like CONCAT('%', UPPER(:query), '%')")
    Page<Bino> findAllByQuery(String query, Pageable pageable);
}