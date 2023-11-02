package com.Paamelding.PaameldingTilFest.Service;

import com.Paamelding.PaameldingTilFest.model.Deltager;
import com.Paamelding.PaameldingTilFest.model.Passord;
import com.Paamelding.PaameldingTilFest.repository.DeltagerRepo;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service

public class PaameldingService {
    @Autowired
    private DeltagerRepo deltagerRepo;
    @Autowired PassordService passordService;
    private static final Logger logger = LoggerFactory.getLogger(PaameldingService.class);


    public Deltager registerUser(String fornavn, String etternavn, String mobil,  String password, String repetertpassord, String kjonn) {
        String salt = passordService.genererTilfeldigSalt();
        String hashedPassword = passordService.hashMedSalt(password, salt);

        Passord passord = new Passord();
        passord.setHash(hashedPassword);
        passord.setSalt(salt);
        Deltager.Kjonn kjonnet;

        if(kjonn.equals("FEMALE")) {
            kjonnet = Deltager.Kjonn.FEMALE;
        } else {
            kjonnet = Deltager.Kjonn.MALE;
        }

        Deltager deltager = new Deltager(fornavn,etternavn, mobil,passord,kjonnet);

        return deltagerRepo.save(deltager);
    }

    public boolean authenticateUser(String mobil, String password) {
        Deltager deltager = findByMobil(mobil);

        if (deltager == null) {
            return false;
        }

        return passordService.erKorrektPassord(password, deltager.getPassord().getSalt(), deltager.getPassord().getHash());
    }



    public Deltager findByMobil(String mobil) {
        return deltagerRepo.findByMobil(mobil);
    }

    public Deltager findByUsername(String username) {
        return findByMobil(username);
    }

    public List<Deltager> finnAlleDeltager() {
        return deltagerRepo.findAllByOrderByFornavnAsc();
    }


}
