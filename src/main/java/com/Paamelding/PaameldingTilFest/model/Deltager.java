package com.Paamelding.PaameldingTilFest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(schema = "deltager")
public class Deltager {

    @Size(min=2, max=20, message="Fornavn må være mellom 2 og 20 tegn og starte med en stor bokstav")
    @Pattern(regexp = "^[A-Z][a-zA-ZæøåÆØÅ-]+$", message="Fornavn kan inneholde bokstaver, bindestrek, og mellomrom. Første tegn skal være en stor bokstav.")
    @Column(name="fornavn", length = 40)
    private String fornavn;

    @Size(min=2, max=20, message="Etternavn må være mellom 2 og 20 tegn og starte med en stor bokstav")
    @Pattern(regexp = "^[A-Z][a-zA-ZæøåÆØÅ-]+$", message="Etternavn kan inneholde bokstaver og bindestrek. Ingen mellomrom tillatt. Første tegn skal være en stor bokstav.")
    @Column(name="etternavn", length = 40)
    private String etternavn;

    @Pattern(regexp = "^\\d{8}$", message="Mobilnummer må være eksakt 8 sifre" )
    @Id
    @Column(name="mobil", length = 8)
    private String mobil;

    @Enumerated(EnumType.STRING)
    @Column(name="kjonn", length=32)
    private Kjonn kjonn;

    @Embedded
    private Passord passord;






    public Deltager() {

    }

    public Deltager(String fornavn, String etternavn, String mobil, Passord passord,Kjonn kjonn) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.mobil = mobil;
        this.passord = passord;
        this.kjonn = kjonn;
    }




    public Passord getPassord() {
        return passord;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public Kjonn getKjonn() {
        return kjonn;
    }

    public void setKjonn(Kjonn kjonn) {
        this.kjonn = kjonn;
    }

    public void setPassord(Passord passord) {
        this.passord = passord;
    }

    public enum Kjonn {
        MALE,
        FEMALE,
    }
}
