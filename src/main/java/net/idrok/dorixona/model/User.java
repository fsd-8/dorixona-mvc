package net.idrok.dorixona.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ism;
    private String familiya;

    @NotNull
    @Size(min = 6, max = 30)
    @Pattern(regexp = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$")
    @Column(unique = true, nullable = false, length = 30)

    private String login;

    @NotNull
    @Size(min = 60, max = 60)
    @Column(unique = true, nullable = false, length = 60)
    private String parol;
    private String telefon;
    @NotNull

    private Lavozim lavozim;

    private boolean aktive = false;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsm() {
        return ism;
    }

    public void setIsm(String ism) {
        this.ism = ism;
    }

    public String getFamiliya() {
        return familiya;
    }

    public void setFamiliya(String familiya) {
        this.familiya = familiya;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getParol() {
        return parol;
    }

    public void setParol(String parol) {
        this.parol = parol;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Lavozim getLavozim() {
        return lavozim;
    }

    public void setLavozim(Lavozim lavozim) {
        this.lavozim = lavozim;
    }

    public boolean isAktive() {
        return aktive;
    }

    public void setAktive(boolean aktive) {
        this.aktive = aktive;
    }
}
