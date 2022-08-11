package net.idrok.dorixona.repository;

import net.idrok.dorixona.model.Bino;
import net.idrok.dorixona.config.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BinoRepository {

    private final DataSource dataSource;
    public BinoRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public  List<Bino> findAll() {
        String sql = "select * from bino;";
        try (PreparedStatement ps = dataSource.ps(sql)) {
            ResultSet rs = ps.executeQuery();
            ArrayList<Bino> list = new ArrayList<>();
            while (rs.next()) {
              list.add(new Bino(rs.getLong("id"), rs.getString("nom"), rs.getString("info")));
            }
            rs.close();
            ps.close();
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
    public  List<Bino> findAll(String key) {
        String sql = "select * from bino WHERE nom ilike ? or info ilike ?;";
        try (PreparedStatement ps = dataSource.ps(sql)) {
            ps.setString(1, "%"+key+"%");
            ps.setString(2, "%"+key+"%");
            ResultSet rs = ps.executeQuery();
            ArrayList<Bino> list = new ArrayList<>();
            while (rs.next()) {
              list.add(new Bino(rs.getLong("id"), rs.getString("nom"), rs.getString("info")));
            }
            rs.close();
            ps.close();
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public  boolean create(Bino bino)  {
        String sql = "insert into bino(nom, info) values (?, ?);";
        try (PreparedStatement ps = dataSource.ps(sql)) {
            ps.setString(1, bino.getNom());
            ps.setString(2, bino.getInfo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);

        }
    }
    public  boolean update(Bino bino)  {
        String sql = "update bino set nom = ?, info = ? where id = ?;";
        try (PreparedStatement ps = dataSource.ps(sql)) {
            ps.setString(1, bino.getNom());
            ps.setString(2, bino.getInfo());
            ps.setLong(3, bino.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public  boolean delete(Bino bino)  {
        return deleteById(bino.getId());
    }

    public boolean deleteById(Long id) {
        String sql = "delete from bino where id = ?;";
        try (PreparedStatement ps = dataSource.ps(sql)) {
            ps.setLong(1, id);
            return  ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public Bino findById(Long id) {
        String sql = "select * from bino where id = ?;";
        try (PreparedStatement ps = dataSource.ps(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Bino bino = null;
            if (rs.next()) {
              bino =  new Bino(rs.getLong("id"), rs.getString("nom"), rs.getString("info"));
            }
            rs.close();
            ps.close();
            return bino;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
}