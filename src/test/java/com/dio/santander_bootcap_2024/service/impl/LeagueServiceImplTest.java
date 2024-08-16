package com.dio.santander_bootcap_2024.service.impl;

import com.dio.santander_bootcap_2024.exceptions.customexceptions.leagueexceptions.LeagueAlreadyExistsException;
import com.dio.santander_bootcap_2024.exceptions.customexceptions.leagueexceptions.LeagueNotFoundException;
import com.dio.santander_bootcap_2024.model.League;
import com.dio.santander_bootcap_2024.repository.LeagueRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeagueServiceImplTest {

    @Mock
    private LeagueRepository leagueRepository;

    @InjectMocks
    private LeagueServiceImpl leagueService;

    @Nested
    public class FindLeagueByIdTests {

        @Test
        void shouldReturnLeagueWhenLeagueExists() throws LeagueNotFoundException {
            // Arrange
            Long leagueId = 1L;
            League mockLeague = new League();
            mockLeague.setId(leagueId);

            when(leagueRepository.findById(leagueId)).thenReturn(Optional.of(mockLeague));

            // Act
            League league = leagueService.findLeagueById(leagueId);

            // Assert
            assertNotNull(league);
            assertEquals(mockLeague, league);
        }

        @Test
        void shouldThrowLeagueNotFoundExceptionWhenLeagueDoesNotExist() {
            // Arrange
            Long leagueId = 1L;
            when(leagueRepository.findById(leagueId)).thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(LeagueNotFoundException.class, () -> leagueService.findLeagueById(leagueId));
        }
    }

    @Nested
    public class FindAllLeaguesTests {

        @Test
        void shouldReturnListOfLeagues() {
            // Arrange
            var mockLeagues = List.of(new League(), new League());
            when(leagueRepository.findAll()).thenReturn(mockLeagues);

            // Act
            List<League> leagues = leagueService.findAllLeagues();

            // Assert
            assertNotNull(leagues);
            assertEquals(mockLeagues, leagues);
        }

        @Test
        void shouldReturnEmptyListWhenNoLeagues() {
            // Arrange
            when(leagueRepository.findAll()).thenReturn(List.of());

            // Act
            List<League> leagues = leagueService.findAllLeagues();

            // Assert
            assertNotNull(leagues);
            assertTrue(leagues.isEmpty());
        }
    }

    @Nested
    public class CreateLeagueTests {

        @Test
        void shouldCreateLeagueWhenNotExists() throws LeagueAlreadyExistsException {
            // Arrange
            League newLeague = new League();
            newLeague.setId(1L);

            when(leagueRepository.existsById(newLeague.getId())).thenReturn(false);
            when(leagueRepository.save(newLeague)).thenReturn(newLeague);

            // Act
            League createdLeague = leagueService.createLeague(newLeague);

            // Assert
            assertNotNull(createdLeague);
            assertEquals(newLeague, createdLeague);
        }

        @Test
        void shouldThrowLeagueAlreadyExistsExceptionWhenLeagueExists() {
            // Arrange
            League existingLeague = new League();
            existingLeague.setId(1L);

            when(leagueRepository.existsById(existingLeague.getId())).thenReturn(true);

            // Act & Assert
            assertThrows(LeagueAlreadyExistsException.class, () -> leagueService.createLeague(existingLeague));
        }
    }

    @Nested
    public class DeleteLeagueTests {

        @Test
        void shouldDeleteLeagueWhenExists() throws LeagueNotFoundException {
            // Arrange
            Long leagueId = 1L;
            when(leagueRepository.existsById(leagueId)).thenReturn(true);

            // Act
            leagueService.deleteLeague(leagueId);

            // Assert
            verify(leagueRepository, times(1)).deleteById(leagueId);
        }

        @Test
        void shouldThrowLeagueNotFoundExceptionWhenLeagueDoesNotExist() {
            // Arrange
            Long leagueId = 1L;
            when(leagueRepository.existsById(anyLong())).thenReturn(false);

            // Act & Assert
            assertThrows(LeagueNotFoundException.class, () -> leagueService.deleteLeague(leagueId));
        }
    }

}