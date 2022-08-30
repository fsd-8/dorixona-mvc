package net.idrok.dorixona.repository;

import net.idrok.dorixona.model.Bino;
import net.idrok.dorixona.model.Dori;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoriRepository extends JpaRepository<Dori, Long> {

}