package com.dio.santander_bootcap_2024.service.impl;

import com.dio.santander_bootcap_2024.controller.factory.PlayerResponseFactory;
import com.dio.santander_bootcap_2024.exceptions.customexceptions.clubexceptions.ClubNotFoundException;
import com.dio.santander_bootcap_2024.exceptions.customexceptions.playerexceptions.PlayerNotFoundException;
import com.dio.santander_bootcap_2024.model.Club;
import com.dio.santander_bootcap_2024.model.Player;
import com.dio.santander_bootcap_2024.repository.ClubRepository;
import com.dio.santander_bootcap_2024.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private ClubRepository clubRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Captor
    private ArgumentCaptor<Long> playerCaptor;


    @Nested
    public class TestesPlayerServiceImpl {

        // ------- Find by id tests ---------- //

        @Test
        final void shouldReturnPlayerWhenFindById() {
            var mockPlayer = PlayerResponseFactory.createPlayer();
            Long mockId = 1L;
            when(playerRepository.findById(mockId)).thenReturn(Optional.of(mockPlayer));

            var playerOutput = playerService.findById(mockId);

            assertNotNull(playerOutput);
            verify(playerRepository, times(1)).findById(mockId);
        }

        @Test
        final void shouldThrowExceptionWhenFindByIdNotFound() {
            Long mockId = 1L;

            when(playerRepository.findById(mockId)).thenReturn(Optional.empty());

            assertThrows(PlayerNotFoundException.class, () -> playerService.findById(mockId));
        }

        @Test
        final void shouldPassCorrectParameters() {
            var mockId = 1L;
            var mockPlayer = PlayerResponseFactory.createPlayer();

            when(playerRepository.findById(playerCaptor.capture())).thenReturn(Optional.of(mockPlayer));

            var playerOutput = playerService.findById(mockId);

            assertEquals(mockId, playerOutput.getId());
            assertEquals(mockPlayer.getName(), playerOutput.getName());
            assertEquals(mockPlayer.getClub(), playerOutput.getClub());
            assertEquals(mockPlayer.getId(), playerOutput.getId());
            assertEquals(mockPlayer.getAge(), playerOutput.getAge());
            assertEquals(mockPlayer.getCountry(), playerOutput.getCountry());
            assertEquals(mockPlayer.getPosition(), playerOutput.getPosition());
            assertEquals(mockPlayer.getShirtNumber(), playerOutput.getShirtNumber());
        }

        // ------- Create player ---------- //

        @Test
        final void shouldReturnPlayerWhenSave() {
            var mockPlayer = PlayerResponseFactory.createPlayer();
            Long mockId = 1L;

            when(playerRepository.save(mockPlayer)).thenReturn(mockPlayer);

            var playerSaved = playerService.create(mockPlayer);

            assertNotNull(playerSaved);
            assertEquals(mockPlayer, playerSaved);
        }

        // ------- Find all tests ---------- //

        @Test
        final void shouldReturnListOfPlayersWhenFindAll() {
            var mockList = PlayerResponseFactory.createPlayers();
            when(playerRepository.findAll()).thenReturn(mockList);

            var resultList = playerService.findAllPlayers();

            assertNotNull(resultList);
            assertEquals(mockList, resultList);
        }

        @Test
        final void shouldReturnEmptyListWhenFindAllisEmpty() {
            Mockito.when(playerRepository.findAll()).thenReturn(List.of());

            var outputList = playerService.findAllPlayers();

            assertTrue(outputList.isEmpty());

        }

        // ------- Delete By ID Tests ---------- //

        @Test
        public void testDeleteById_PlayerExists() {
            Long playerId = 1L;
            when(playerRepository.existsById(playerId)).thenReturn(true);

            playerService.deleteById(playerId);

            verify(playerRepository, times(1)).deleteById(playerId);
        }

        @Test
        final void shouldThrowExceptionWhenDeleteByIdNotFound() {
            Long mockId = 1L;

            when(playerRepository.existsById(mockId)).thenReturn(false);

            assertThrows(PlayerNotFoundException.class, () -> playerService.deleteById(mockId));

        }

        // ------- Assign Club to Player Tests ---------- //

        @Test
        void shouldAssignClubToPlayerWhenPlayerAndClubExist() throws PlayerNotFoundException, ClubNotFoundException {
            // Arrange
            Long playerId = 1L;
            Long clubId = 1L;
            Player player = new Player();
            Club club = new Club();

            when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
            when(clubRepository.findById(clubId)).thenReturn(Optional.of(club));

            // Act
            playerService.assignClubToPlayer(playerId, clubId);

            // Assert
            verify(playerRepository).save(player);
            assertEquals(club, player.getClub());
        }

        @Test
        void shouldThrowPlayerNotFoundExceptionWhenPlayerDoesNotExist() {
            // Arrange
            Long playerId = 1L;
            Long clubId = 1L;
            when(playerRepository.findById(playerId)).thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(PlayerNotFoundException.class, () -> playerService.assignClubToPlayer(playerId, clubId));
        }

        @Test
        void shouldThrowClubNotFoundExceptionWhenClubDoesNotExist() {
            // Arrange
            Long playerId = 1L;
            Long clubId = 1L;
            Player player = new Player();

            when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
            when(clubRepository.findById(clubId)).thenReturn(Optional.empty());

            // Act & Assert
            assertThrows(ClubNotFoundException.class, () -> playerService.assignClubToPlayer(playerId, clubId));
        }
        

    }


}