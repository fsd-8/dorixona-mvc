package net.idrok.dorixona.model;


import javax.persistence.*;

@Entity
public class Xona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @ManyToOne
    private Bino bino;
    private String info;

    @Lob // Large object
    private byte[] rasm;


    public Xona() {
    }
    public Xona(Long id, String nom, Bino bino, String info) {
        this.id = id;
        this.nom = nom;
        this.bino = bino;
        this.info = info;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Bino getBino() {
        return bino;
    }
    public void setBino(Bino bino) {
        this.bino = bino;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public byte[] getRasm() {
        return rasm;
    }

    public void setRasm(byte[] rasm) {
        this.rasm = rasm;
    }
}
