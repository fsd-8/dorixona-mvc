package net.idrok.dorixona.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
@EnableConfigurationProperties
public class DataSource {
    private Connection connection;


    public final   String url;

    public final String username;

    public final String password;

    public DataSource( @Value("${datasource.url}") String url, @Value("${datasource.username}") String username, @Value("${datasource.password}") String password){
        this.url = url;
        this.username = username;
        this.password = password;
        if(connect()){
          System.out.println("Database connected");
      } else {
          System.out.println("Database not connected");
      }
    }

    private boolean connect(){
        try {
            connection = DriverManager.getConnection(url, username, password);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public PreparedStatement ps(String sql) throws SQLException {
        if(connection == null){
            if(!connect()){
                throw new SQLException("database not connected yet");
            }
        }
        return connection.prepareStatement(sql);
    }


}
