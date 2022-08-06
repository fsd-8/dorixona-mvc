package net.idrok.dorixona.model;

public class Bino {
    private Long id;
    private String nom;
    private String info;

    public Bino() {
    }

    public Bino(Long id, String nom, String info) {
        this.id = id;
        this.nom = nom;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
