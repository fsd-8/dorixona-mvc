package net.idrok.dorixona.repository;

import net.idrok.dorixona.config.DataSource;
import net.idrok.dorixona.model.Bino;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BinoRepository {
    private final DataSource ds;

    public BinoRepository(DataSource ds) {
        this.ds = ds;
    }


    //    public List<Bino> findAll() {
//        List<Bino> binolar = new ArrayList<>();
//        binolar.add(new Bino(1L, "Asosiy", "asosiy bino"));
//        binolar.add(new Bino(2L, "qoshimcha", "f bino"));
//        binolar.add(new Bino(3L, "Omborxona", "a bino"));
//        return binolar;
//    }
    public List<Bino> findAll() {
        String sql = "...";
        try {
            PreparedStatement ps = ds.ps(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Bino> binolar = new ArrayList<>();
            //...
            return binolar;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
