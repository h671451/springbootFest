package com.Paamelding.PaameldingTilFest.Service;

import com.Paamelding.PaameldingTilFest.model.Deltager;
import com.Paamelding.PaameldingTilFest.model.Passord;
import com.Paamelding.PaameldingTilFest.repository.DeltagerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import java.rmi.UnexpectedException;
import java.util.List;

@Service

public class PaameldingService {
    @Autowired
    private DeltagerRepo deltagerRepo;
    @Autowired PassordService passordService;

    public Passord hashedPassord(String passord){
        String salt = passordService.genererTilfeldigSalt();
        String hashedPassword = passordService.hashMedSalt(passord, salt);

        Passord passordet = new Passord();
        passordet.setHash(hashedPassword);
        passordet.setSalt(salt);
        return passordet;
    }

    public Deltager registerUser(String fornavn, String etternavn, String mobil,  String password, String kjonn) {

        Deltager.Kjonn kjonnet;


        if(kjonn.equals("FEMALE")) {
            kjonnet = Deltager.Kjonn.FEMALE;
        } else {
            kjonnet = Deltager.Kjonn.MALE;
        }


        Deltager deltager = new Deltager(fornavn,etternavn, mobil,hashedPassord(password),kjonnet);


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


    public List<Deltager> finnAlleDeltager() {
        return deltagerRepo.findAllByOrderByFornavnAsc();
    }


    public boolean existsByMobil(String mobil) {
        return deltagerRepo.existsByMobil(mobil);
    }
}
