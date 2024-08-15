package com.dio.santander_bootcap_2024.controller;

import com.dio.santander_bootcap_2024.controller.factory.LeagueResponseFactory;
import com.dio.santander_bootcap_2024.model.League;
import com.dio.santander_bootcap_2024.service.LeagueService;
import org.apache.coyote.Response;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeagueControllerTest {

    @Mock
    LeagueService leagueService;

    @InjectMocks
    LeagueController leagueController;

    @Captor
    ArgumentCaptor<Long> argumentCaptor;

    @Nested
    public class LeagueControllerTests {

        // ---------- Find By ID Tests ------------ //

        @Test
        final void shouldReturnHttpStatusOkWhenFindLeagueById() {
            // Arrange
            var leagueId = 1L;
            var mockLeague = LeagueResponseFactory.buildLeague();
            when(leagueService.findLeagueById(anyLong())).thenReturn(mockLeague);
            // Act
            var response = leagueController.findById(leagueId);
            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(mockLeague, response.getBody());
        }

        @Test
        final void shouldPassCorrectParametersToServiceClassInFindLeagueById() {
            // Arrange
            Long leagueId = 1L;
            var mockLeague = LeagueResponseFactory.buildLeague();
            when(leagueService.findLeagueById(argumentCaptor.capture())).thenReturn(mockLeague);
            // Act
            var response = leagueController.findById(leagueId);
            // Assert
            assertEquals(leagueId, argumentCaptor.getValue());
            verify(leagueService, times(1)).findLeagueById(argumentCaptor.getValue());
        }

        @Test
        final void shouldReturnCorrectResponseBody() {
            // Arrange
            Long leagueId = 1L;
            var mockLeague = LeagueResponseFactory.buildLeague();
            when(leagueService.findLeagueById(anyLong())).thenReturn(mockLeague);
            // Act
            var response = leagueController.findById(leagueId);
            // Assert
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(mockLeague, response.getBody());
        }

        // ---------- Find All ------------ //

        @Test
        final void shouldReturnHttpStatusOkWhenFindAll() {
            var mockList = LeagueResponseFactory.buildLeagueList();
            when(leagueService.findAllLeagues()).thenReturn(mockList);

            var response = leagueController.findAll();

            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        final void shouldReturnCorrectResponseBodyWhenFindAllLeagues() {
            var mockList = LeagueResponseFactory.buildLeagueList();
            when(leagueService.findAllLeagues()).thenReturn(mockList);

            var response = leagueController.findAll();

            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(mockList, response.getBody());
        }

        @Test
        final void shouldReturnEmptyListWhenFindAllLeagues() {
            List<League> mockList = List.of();
            when(leagueService.findAllLeagues()).thenReturn(mockList);

            var response = leagueController.findAll();

            assertTrue(response.getBody().isEmpty());
        }

        // ---------- Create League Tests ------------ //

        @Test
        final void shouldReturnHttpStatusCreatedWhenCreateLeague() {
            var leagueCreated = LeagueResponseFactory.buildLeague();
            var leagueToCreate = LeagueResponseFactory.buildLeague();
            when(leagueService.createLeague(any())).thenReturn(leagueCreated);

            var response = leagueController.createLeague(leagueToCreate);

            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertEquals(leagueCreated, response.getBody());
        }

        // ---------- Delete League Tests ------------ //

        @Test
        final void shouldReturnHttpStatusNoContentWhenDeleteLeague() {
            Long leagueId = 1L;

            ResponseEntity<Void> response = leagueController.deleteLeague(leagueId);

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            verify(leagueService, times(1)).deleteLeague(leagueId);
        }

    }

}