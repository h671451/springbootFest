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
import java.util.Optional;

@Service

public class PaameldingService {
    @Autowired
    private DeltagerRepo deltagerRepo;
    @Autowired PassordService passordService;
    private static final Logger logger = LoggerFactory.getLogger(PaameldingService.class);




    public void registerUser(String mobil, String password) {
        String salt = passordService.genererTilfeldigSalt();
        String hashedPassword = passordService.hashMedSalt(password, salt);

        Deltager deltager = new Deltager();
        deltager.setMobil(mobil);
        Passord passord = new Passord();
        passord.setHash(hashedPassword);
        passord.setSalt(salt);
        //passord.setPassord(null);  // Clear the plaintext password from memory

        deltager.setPassord(passord);

        deltagerRepo.save(deltager);
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
        return deltagerRepo.findAll();
    }

    public Optional<Deltager> getDeltagerById(String mobil) {
        return deltagerRepo.findById(mobil);
    }

    public Deltager saveDeltager(Deltager deltager) {
        return deltagerRepo.save(deltager);
    }

    public Deltager updateDeltager(Deltager deltager) {
        if (deltagerRepo.existsById(deltager.getMobil())) {
            return deltagerRepo.save(deltager);
        }
        throw new EntityNotFoundException("Deltager with ID " + deltager.getMobil() + " not found.");
    }

    public void deleteDeltager(String mobil) {
        deltagerRepo.deleteById(mobil);
    }


    public void addDeltager(Deltager deltager) {
        try {
            deltagerRepo.save(deltager);
        } catch (Exception e) {
            logger.error("Error while adding Deltager: ", e);
            throw e; // rethrowing to ensure the error is handled up in the call hierarchy or to rollback the transaction
        }
    }

}
