package com.dio.santander_bootcap_2024.controller;

import com.dio.santander_bootcap_2024.controller.factory.PlayerResponseFactory;
import com.dio.santander_bootcap_2024.exceptions.GlobalExceptionHandler;
import com.dio.santander_bootcap_2024.exceptions.customexceptions.playerexceptions.PlayerNotFoundException;
import com.dio.santander_bootcap_2024.model.Player;
import com.dio.santander_bootcap_2024.service.PlayerService;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @Mock
    PlayerService playerService;

    @InjectMocks
    PlayerController playerController;

    @Captor
    ArgumentCaptor<Long> playerIdCaptor;

    @Nested
    class ControllerTests {

        //------------- findById Tests -------------//
        @Test
        void shouldReturnHttpStatusOkWhenFindById() {
            //Arrange
            Long playerId = 1L;
            Player mockPlayer = PlayerResponseFactory.createPlayer();
            when(playerService.findById(anyLong())).thenReturn(mockPlayer);
            //Act
            ResponseEntity<Player> response = playerController.findById(playerId);
            //Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(mockPlayer, response.getBody()); // deve ser criado um teste separado para verificar
        }

        @Test
        void shouldPassCorrectParametersToServiceClass() {
            //Arrange
            Long playerId = 1L;
            Player mockPlayer = PlayerResponseFactory.createPlayer();
            when(playerService.findById(playerIdCaptor.capture())).thenReturn(mockPlayer);
            //Act
            ResponseEntity<Player> response = playerController.findById(playerId);
            //Assert
            assertEquals(playerId, playerIdCaptor.getValue());
        }

        @Test
        void shouldReturnResponseBodyCorrectly() {
            //Arrange
            Long playerId = 1L;
            Player mockPlayer = PlayerResponseFactory.createPlayer();
            when(playerService.findById(anyLong())).thenReturn(mockPlayer);
            //Act
            ResponseEntity<Player> response = playerController.findById(playerId);
            //Assert
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(mockPlayer, response.getBody());

        }

        // ---------- FindAll tests ----------- //
        @Test
        final void shouldReturnHttpStatusOkWhenFindAll() {
            //Arrange
            List<Player> mockPlayers = PlayerResponseFactory.createPlayers();
            when(playerService.findAllPlayers()).thenReturn(mockPlayers);
            //Act
            var response = playerController.findAll();
            //Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(mockPlayers, response.getBody());
        }

        @Test
        void shouldReturnResponseBodyCorrectlyFindAll() {
            //Arrange
            List<Player> mockPlayers = PlayerResponseFactory.createPlayers();
            when(playerService.findAllPlayers()).thenReturn(mockPlayers);
            //Act
            var response = playerController.findAll();
            //Assert
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(mockPlayers, response.getBody());

        }

        @Test
        void shouldReturnEmptyListWhenNoPlayersFoundFindAll() {
            // Arrange
            when(playerService.findAllPlayers()).thenReturn(List.of());

            // Act
            ResponseEntity<List<Player>> response = playerController.findAll();

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.getBody().isEmpty());
        }

        // --------- Create Player tests ---------- //
        @Test
        final void shouldReturnHttpStatusCreatedWhenCreatePlayer() {
            // Arrange
            Player playerToCreate = PlayerResponseFactory.createPlayer();
            Player playerCreated = PlayerResponseFactory.createPlayer();
            when(playerService.create(playerToCreate)).thenReturn(playerCreated);
            // Act
            var response = playerController.create(playerToCreate);
            // Assert
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertEquals(playerCreated, response.getBody());
        }

        // --------- Delete Player tests ---------- //
        @Test
        final void shouldReturnNoContentWhenDeleteIsSucesfull() {
            // Arrange
            Long playerId = 1L;

            // Act
            ResponseEntity<Void> response = playerController.deleteById(playerId);

            // Assert
            verify(playerService, times(1)).deleteById(playerId);
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            assertNull(response.getBody()); // Para o status 204, o corpo da resposta deve ser nulo
        }

        // --------- Assign Club to a  Player tests ---------- //

        @Test
        void shouldReturnAcceptedWhenAssigningClubToPlayerSuccessfully() {
            // Arrange
            Long playerId = 1L;
            Long clubId = 1L;

            // Act
            ResponseEntity<Void> response = playerController.assignClubToPlayer(playerId, clubId);

            // Assert
            verify(playerService).assignClubToPlayer(playerId, clubId);
            assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
            assertNull(response.getBody()); // Para o status 202, o corpo da resposta deve ser nulo
        }

    }

}