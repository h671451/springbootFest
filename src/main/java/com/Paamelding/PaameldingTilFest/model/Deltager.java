package com.Paamelding.PaameldingTilFest.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

import javax.annotation.processing.Generated;


public class Deltager {
    @Size(min=2, message="Fornavn må inneholde minst 2 tegn")
    @NotNull(message = "fornavn er obligatorisk")
    private String fornavn;
    @Size(min=2, message="Etternavn må inneholde minst 2 tegn")
    @NotNull(message = "etternavn er obligatorisk")
    private String etternavn;
    @Pattern(regexp = "^\\d{8}$", message="Mobilnummer må være eksakt 8 sifre" )
    @NotNull(message = "Mobil er obligatorisk")
    @Id
    private Integer mobil;
    @NotNull(message = "kjonn er obligatorisk")
    private Kjonn kjonn;

    private String passord;

    public Deltager() {

    }

    public Deltager(String fornavn, String etternavn, Integer mobil, String passord, Kjonn kjonn) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.mobil = mobil;
        this.passord = passord;
        this.kjonn = kjonn;
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

    public Integer getMobil() {
        return mobil;
    }

    public void setMobil(Integer mobil) {
        this.mobil = mobil;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public Kjonn getKjonn() {
        return kjonn;
    }

    public void setKjonn(Kjonn kjonn) {
        this.kjonn = kjonn;
    }


    public enum Kjonn {
        MALE,
        FEMALE,
    }
}
