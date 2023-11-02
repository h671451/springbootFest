package com.Paamelding.PaameldingTilFest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Passord {

    @Column(name = "hash", length = 64, nullable = false)
    private String hash;

    @Column(name = "salt", length = 32, nullable = false)
    private String salt;

    // Getter and Setter methods for hash and salt

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
