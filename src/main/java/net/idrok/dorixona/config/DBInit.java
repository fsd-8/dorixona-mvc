package net.idrok.dorixona.config;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DBInit {

    private final DataSource dataSource;

    public DBInit(DataSource dataSource){
        this.dataSource = dataSource;
    }
    

    @PostConstruct
    public void createBino() throws SQLException{
        // jadval yaratish
        String createTable = "CREATE TABLE IF NOT EXISTS bino(id serial primary key, nom name, info text);";
        if(dataSource.ps(createTable).executeUpdate()>0){
            System.out.println("Bino jadvali yaratildi");
        } else {
            System.err.println("Bino jadvali yaratilmadi");
        }
    }
    @PostConstruct
    public void createXona() throws SQLException{
        // jadval yaratish
        String createTable = "CREATE TABLE IF NOT EXISTS xona(id serial primary key, bino integer, nom name, info text);";
        if(dataSource.ps(createTable).executeUpdate()>0){
            System.out.println("Xona jadvali yaratildi");
            String alterTable = "ALTER TABLE xona add constraint bino reference bino(id);";
            if(dataSource.ps(alterTable).executeUpdate()>0){
                System.out.println("Xona - bino bog'landi");
            }
        } else {
            System.err.println("Xona jadvali yaratilmadi");
        }



    }




}
