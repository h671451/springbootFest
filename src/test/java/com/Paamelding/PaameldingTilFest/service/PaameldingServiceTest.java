package com.Paamelding.PaameldingTilFest.service;

import com.Paamelding.PaameldingTilFest.Service.PaameldingService;
import com.Paamelding.PaameldingTilFest.Service.PassordService;
import com.Paamelding.PaameldingTilFest.model.Deltager;
import com.Paamelding.PaameldingTilFest.model.Passord;
import com.Paamelding.PaameldingTilFest.repository.DeltagerRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class PaameldingServiceTest {

    @Mock
    private DeltagerRepo deltagerRepo;

    @Mock
    private PassordService passordService;

    @InjectMocks
    private PaameldingService paameldingService;

    @BeforeEach
    void setUp() {
        // Set up any common mocking behavior here
    }

    @Test
    void hashedPassord_ShouldReturnHashedPassword() {
        // Arrange
        String rawPassword = "password123";
        String salt = "randomSalt";
        String hashed = "hashedPassword123";

        when(passordService.genererTilfeldigSalt()).thenReturn(salt);
        when(passordService.hashMedSalt(rawPassword, salt)).thenReturn(hashed);

        // Act
        Passord result = paameldingService.hashedPassord(rawPassword);

        // Assert
        assertThat(result.getHash()).isEqualTo(hashed);
        assertThat(result.getSalt()).isEqualTo(salt);
    }



    @Test
    void authenticateUser_ShouldReturnTrueWhenCredentialsAreValid() {
        // Arrange
        String mobil = "12345678";
        String password = "password123";
        Deltager existingDeltager = mock(Deltager.class);
        Passord passord = new Passord();

        when(deltagerRepo.findByMobil(mobil)).thenReturn(existingDeltager);
        when(existingDeltager.getPassord()).thenReturn(passord);
        when(passordService.erKorrektPassord(password, passord.getSalt(), passord.getHash())).thenReturn(true);

        // Act
        boolean result = paameldingService.authenticateUser(mobil, password);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    void findByMobil_ShouldReturnDeltager() {
        // Arrange
        String mobil = "12345678";
        Deltager expectedDeltager = new Deltager();
        when(deltagerRepo.findByMobil(mobil)).thenReturn(expectedDeltager);

        // Act
        Deltager result = paameldingService.findByMobil(mobil);

        // Assert
        assertThat(result).isEqualTo(expectedDeltager);
    }

    @Test
    void finnAlleDeltager_ShouldReturnListOfDeltager() {
        // Arrange
        List<Deltager> expectedList = Arrays.asList(new Deltager(), new Deltager());
        when(deltagerRepo.findAllByOrderByFornavnAsc()).thenReturn(expectedList);

        // Act
        List<Deltager> result = paameldingService.finnAlleDeltager();

        // Assert
        assertThat(result).isEqualTo(expectedList);
    }

    @Test
    void existsByMobil_ShouldReturnTrueIfMobilExists() {
        // Arrange
        String mobil = "12345678";
        when(deltagerRepo.existsByMobil(mobil)).thenReturn(true);

        // Act
        boolean result = paameldingService.existsByMobil(mobil);

        // Assert
        assertThat(result).isTrue();
    }
}
