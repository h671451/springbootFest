package com.Paamelding.PaameldingTilFest.model;

import com.Paamelding.PaameldingTilFest.Service.PassordService;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.beans.factory.annotation.Autowired;

@Embeddable
public class Passord {
    @Autowired
    PassordService passordService;


    @Column(name = "hash", length = 64, nullable = false)
    private String hash;

    @Column(name = "salt", length = 32, nullable = false)
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
