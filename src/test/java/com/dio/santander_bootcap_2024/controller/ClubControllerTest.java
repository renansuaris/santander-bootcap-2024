package com.dio.santander_bootcap_2024.controller;

import com.dio.santander_bootcap_2024.controller.factory.ClubResponseFactory;
import com.dio.santander_bootcap_2024.controller.factory.PlayerResponseFactory;
import com.dio.santander_bootcap_2024.model.Club;
import com.dio.santander_bootcap_2024.model.Player;
import com.dio.santander_bootcap_2024.service.ClubService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClubControllerTest {

    @Mock
    ClubService clubService;

    @InjectMocks
    ClubController clubController;

    @Captor
    ArgumentCaptor<Long> clubIdCaptor;

    @Nested
    class ControllerTests {

        //------------- findById Tests -------------//
         @Test
         final void shouldReturnHttpStatusOkWhenGetClubById() {
             // Arrange
             Long clubId = 1L;
             var mockClub = ClubResponseFactory.buildClub();
             when(clubService.findClubById(anyLong())).thenReturn(mockClub);
             // Act
             var output = clubController.getClubById(clubId);
             // Assert
             assertEquals(HttpStatus.OK, output.getStatusCode());
             assertEquals(mockClub, output.getBody());
         }

         @Test
         void shouldPassCorrectParametersToServiceClass() {
             // Arrange
             Long clubId = 1L;
             var mockClub = ClubResponseFactory.buildClub();
             when(clubService.findClubById(clubIdCaptor.capture())).thenReturn(mockClub);
             // Act
             var output = clubController.getClubById(clubId);
             // Assert
             assertEquals(clubId, clubIdCaptor.getValue());
             verify(clubService, times(1)).findClubById(clubId);
         }

         @Test
         final void shouldReturnResponseBodyCorrectly() {
             // Arrange
             Long clubId = 1L;
             var mockClub = ClubResponseFactory.buildClub();
             when(clubService.findClubById(anyLong())).thenReturn(mockClub);
             // Act
             var response = clubController.getClubById(clubId);
             // Assert
             assertNotNull(response);
             assertNotNull(response.getBody());
             assertEquals(mockClub, response.getBody());
         }

        // ---------- get All Clubs Tests ----------- //

        @Test
        final void shouldReturnHttpStatusOkWhenFindAll() {
             //Arrange
             var mockClubs = ClubResponseFactory.buildListClubs();
             when(clubService.findAllClubs()).thenReturn(mockClubs);
             // Act
            var response = clubController.getAllClubs();
            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(mockClubs, response.getBody());
        }

        @Test
        final void shouldReturnResponseBodyCorrectlyWhenFindAll() {
             // Arrange
             var mockClubs = ClubResponseFactory.buildListClubs();
             when(clubService.findAllClubs()).thenReturn(mockClubs);
             // Act
            var output = clubController.getAllClubs();
            // Assert
            assertNotNull(output);
            assertNotNull(output.getBody());
            assertEquals(mockClubs, output.getBody());
        }

        @Test
        final void shouldReturnEmptyListWhenNotFoundFindAll() {
             // Arrange
            when(clubService.findAllClubs()).thenReturn(List.of());
            // Act
            var response = clubController.getAllClubs();
            // Assert
            assertTrue(response.getBody().isEmpty()); // verifying if the output is empty as expected
        }

        // --------- Create Club tests ---------- //

        @Test
        final void shouldReturnHttpStatusOkWhenCreateClub() {
             // Arrange
             var clubToCreate = ClubResponseFactory.buildClub();
             var clubCreated = ClubResponseFactory.buildClub();
             when(clubService.createClub(clubToCreate)).thenReturn(clubCreated);
             // Act
            var response = clubController.createClub(clubToCreate);
            // Assert
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertEquals(clubCreated, response.getBody());
        }

        // --------- Delete Club tests ---------- //

        @Test
        final void shouldReturnNoContentWhenDeleteClub() {
             //Arrange
             var clubId = 1L;
             //Act
             var response = clubController.deleteClubById(clubId);
             //Assert
            verify(clubService, times(1)).deleteClubById(clubId);
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            assertNull(response.getBody());
        }

        // --------- Assign League to a Club tests ---------- //

        @Test
        final void shouldReturnHttpStatusAcceptedWhenAssingLeagueToClub() {
             // Arrange
             var clubId = 1l;
             var leagueId = 2l;
             // Act
            var response = clubController.assignLeagueToClub(clubId, leagueId);
            // Assert
            verify(clubService, times(1)).assignLeagueToClub(clubId, leagueId);
            assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
            assertNull(response.getBody());
        }
    }

}