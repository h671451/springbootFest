package com.Paamelding.PaameldingTilFest.model;

import jakarta.persistence.Column;

public class Passord {
    @Column(name="hash", length=64)

    private String hash;
    @Column(name="salt", length=32)

    private String salt;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


}
