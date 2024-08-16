package com.dio.santander_bootcap_2024.service.impl;

import com.dio.santander_bootcap_2024.controller.factory.ClubResponseFactory;
import com.dio.santander_bootcap_2024.controller.factory.LeagueResponseFactory;
import com.dio.santander_bootcap_2024.exceptions.customexceptions.clubexceptions.ClubAlreadyExistsException;
import com.dio.santander_bootcap_2024.exceptions.customexceptions.clubexceptions.ClubNotFoundException;
import com.dio.santander_bootcap_2024.exceptions.customexceptions.leagueexceptions.LeagueNotFoundException;
import com.dio.santander_bootcap_2024.model.Club;
import com.dio.santander_bootcap_2024.model.League;
import com.dio.santander_bootcap_2024.repository.ClubRepository;
import com.dio.santander_bootcap_2024.repository.LeagueRepository;
import org.assertj.core.api.Assertions;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClubServiceImplTest {

    @Mock
    ClubRepository clubRepository;

    @Mock
    LeagueRepository leagueRepository;

    @InjectMocks
    ClubServiceImpl clubService;

    @Nested
    public class ClubServiceTests {

        // ------- Find By Id Tests -------- //

        @Test
        final void shouldReturnClubWhenFindClubById() {
            // Arrange
            Long mockId = 1L;
            var mockClub = ClubResponseFactory.buildClub();
            when(clubRepository.findById(anyLong())).thenReturn(Optional.of(mockClub));
            // Act
            var clubReturn = clubService.findClubById(mockId);
            // Assert
            Assertions.assertThat(clubReturn).isNotNull();
            assertEquals(mockClub, clubReturn);
        }

        @Test
        final void shouldThrowExceptionWhenFindClubByIdNotFound() {
            var mockClub = ClubResponseFactory.buildClub();
            when(clubRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(ClubNotFoundException.class, () -> clubService.findClubById(mockClub.getId()));
        }

        // ------- Create Club Tests -------- //
        @Test
        final void shouldReturnClubWhenCreateClub() throws ClubAlreadyExistsException {
            var clubToCreate = ClubResponseFactory.buildClub();
            when(clubRepository.existsById(anyLong())).thenReturn(false);
            when(clubRepository.save(any(Club.class))).thenReturn(clubToCreate);

            var clubCreated = clubService.createClub(clubToCreate);

            assertNotNull(clubCreated);
            assertEquals(clubToCreate, clubCreated);
        }

        @Test
        final void shouldThrowClubAlreadyExistsExceptionWhenCreateClub() {
            var clubToCreate = ClubResponseFactory.buildClub();
            when(clubRepository.existsById(anyLong())).thenReturn(true);

            assertThrows(ClubAlreadyExistsException.class, () -> clubService.createClub(clubToCreate));
        }

        // ------- Find All Clubs Tests -------- //
        @Test
        final void shouldReturnListOfClubsWhenFindAll() {
            List<Club> mockList = List.of(ClubResponseFactory.buildClub(), ClubResponseFactory.buildClub());
            when(clubRepository.findAll()).thenReturn(mockList);

            var output = clubService.findAllClubs();

            assertNotNull(output);
            assertEquals(mockList, output);
        }

        @Test
        final void shouldReturnEmptyListWhenFindAllNotFound() {
            when(clubRepository.findAll()).thenReturn(List.of());

            var output = clubService.findAllClubs();

            assertTrue(output.isEmpty());
        }

        // ------- Delete Club By Id Tests -------- //
        @Test
        final void shouldDeleteClubByIdWhenDeleteClub() {
            Long mockId = 1L;
            when(clubRepository.existsById(anyLong())).thenReturn(true);

            clubService.deleteClubById(mockId);

            verify(clubRepository, times(1)).deleteById(mockId);
        }

        @Test
        final void shouldThrowExceptionWhenDeleteClubByIdNotFound() {
            var mockId = 1L;
            when(clubRepository.existsById(anyLong())).thenReturn(false);

            assertThrows(ClubNotFoundException.class, () -> clubService.deleteClubById(mockId));
        }

        // ------- Delete Club By Id Tests -------- //

        @Test
        final void shouldAssignLeagueToClubSuccessfully() throws ClubNotFoundException, LeagueNotFoundException {
            // Arrange
            Long clubId = 1L;
            Long leagueId = 2L;
            Club mockClub = ClubResponseFactory.buildClub();
            League mockLeague = LeagueResponseFactory.buildLeague();

            when(clubRepository.findById(clubId)).thenReturn(Optional.of(mockClub));
            when(leagueRepository.findById(leagueId)).thenReturn(Optional.of(mockLeague));
            when(clubRepository.save(any(Club.class))).thenReturn(mockClub);

            // Act
            clubService.assignLeagueToClub(clubId, leagueId);

            // Assert
            assertEquals(mockLeague, mockClub.getLeague());
            verify(clubRepository, times(1)).save(mockClub);
        }

        @Test
        final void shouldThrowClubNotFoundExceptionWhenAssigningLeagueToNonExistentClub() {
            // Arrange
            Long clubId = 1L;
            Long leagueId = 2L;
            when(clubRepository.findById(clubId)).thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(ClubNotFoundException.class, () -> clubService.assignLeagueToClub(clubId, leagueId));
        }

        @Test
        final void shouldThrowLeagueNotFoundExceptionWhenAssigningNonExistentLeague() {
            // Arrange
            Long clubId = 1L;
            Long leagueId = 2L;
            Club mockClub = ClubResponseFactory.buildClub();
            when(clubRepository.findById(clubId)).thenReturn(Optional.of(mockClub));
            when(leagueRepository.findById(leagueId)).thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(LeagueNotFoundException.class, () -> clubService.assignLeagueToClub(clubId, leagueId));
        }

    }

}