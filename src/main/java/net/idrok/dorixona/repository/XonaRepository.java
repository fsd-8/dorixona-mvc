package net.idrok.dorixona.repository;

import net.idrok.dorixona.model.Xona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XonaRepository extends JpaRepository<Xona, Long> {

}