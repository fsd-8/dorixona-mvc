package net.idrok.dorixona.repository;

import net.idrok.dorixona.model.Bino;
import net.idrok.dorixona.model.Xona;
import net.idrok.dorixona.config.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class XonaRepository {

    private final DataSource dataSource;
    public XonaRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public  List<Xona> findAll() {
        String sql = "select x.id, x.nom, b.id as bino_id, b.nom as bino_nom, b.info as bino_info, x.info "+
                    " from xona as x inner join bino as b on x.bino = b.id;";
        try (PreparedStatement ps = dataSource.ps(sql)) {
            ResultSet rs = ps.executeQuery();
            ArrayList<Xona> list = new ArrayList<>();
            while (rs.next()) {
              list.add(new Xona(rs.getLong("id"),  rs.getString("nom"), new Bino(rs.getLong("bino_id"), rs.getString("bino_nom"), rs.getString("bino_info")), rs.getString("info")));
            }
            rs.close();
            ps.close();
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
    public  List<Xona> findAll(String key) {
        String sql = 
        "select x.id, x.nom, b.id as bino_id, b.nom as bino_nom, b.info as bino_info, x.info "+
                    " from xona as x inner join bino as b on x.bino = b.id " +
        " WHERE x.nom ilike ? or x.info ilike ? or b.nom ilike ?;";
        try (PreparedStatement ps = dataSource.ps(sql)) {
            ps.setString(1, "%"+key+"%");
            ps.setString(2, "%"+key+"%");
            ps.setString(3, "%"+key+"%");
            ResultSet rs = ps.executeQuery();
            ArrayList<Xona> list = new ArrayList<>();
            while (rs.next()) {
              list.add(new Xona(rs.getLong("id"),  rs.getString("nom"), new Bino(rs.getLong("bino_id"), rs.getString("bino_nom"), rs.getString("bino_info")), rs.getString("info")));
            }
            rs.close();
            ps.close();
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public  boolean create(Xona xona)  {
        String sql = "insert into xona(nom, bino, info) values (?, ?, ?);";
        try (PreparedStatement ps = dataSource.ps(sql)) {
            ps.setString(1, xona.getNom());
            ps.setLong(2, xona.getBino().getId());
            ps.setString(3, xona.getInfo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);

        }
    }
    public  boolean update(Xona xona)  {
        String sql = "update xona set nom = ?, bino = ?, info = ? where id = ?;";
        try (PreparedStatement ps = dataSource.ps(sql)) {
            ps.setString(1, xona.getNom());
            ps.setLong(2, xona.getBino().getId());

            ps.setString(3, xona.getInfo());
            ps.setLong(4, xona.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public  boolean delete(Xona xona)  {
        return deleteById(xona.getId());
    }

    public boolean deleteById(Long id) {
        String sql = "delete from xona where id = ?;";
        try (PreparedStatement ps = dataSource.ps(sql)) {
            ps.setLong(1, id);
            return  ps.executeUpdate() > 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public Xona findById(Long id) {
        String sql =  "select x.id, x.nom, b.id as bino_id, b.nom as bino_nom, b.info as bino_info, x.info "+
        " from xona as x inner join bino as b on x.bino = b.id where x.id = ?;";
        try (PreparedStatement ps = dataSource.ps(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Xona Xona = null;
            if (rs.next()) {
              Xona =  new Xona(rs.getLong("id"),  rs.getString("nom"), new Bino(rs.getLong("bino_id"), rs.getString("bino_nom"), rs.getString("bino_info")), rs.getString("info"));
            }
            rs.close();
            ps.close();
            return Xona;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
}