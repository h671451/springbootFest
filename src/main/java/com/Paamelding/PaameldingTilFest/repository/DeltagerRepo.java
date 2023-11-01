package com.Paamelding.PaameldingTilFest.repository;

import com.Paamelding.PaameldingTilFest.model.Deltager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeltagerRepo extends JpaRepository<Deltager,String> {
    Deltager findByMobil(String mobil);
}
