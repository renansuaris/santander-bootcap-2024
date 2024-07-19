package com.dio.santander_bootcap_2024;

import com.dio.santander_bootcap_2024.model.Player;
import com.dio.santander_bootcap_2024.repository.PlayerRepository;
import com.dio.santander_bootcap_2024.service.PlayerService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class SantanderBootcap2024ApplicationTests {

/*
	@Autowired
	PlayerService service;

	@MockBean
	PlayerRepository playerRepository;

	@Test
	public void mustReturnPlayerByTheirName() {
		Player player = new Player();
		player.setName("Neymar");
		player.setAge(32);
		player.setCountry("Brazil");
		player.setHeight("172");
		player.setShirtNumber(10);
		player.setPosition("CAM");

		when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));

		Player foundPlayer = service.findById(player.getId());

		verify(playerRepository, times(1)).findById(player.getId());

		assertEquals(player.getId(), foundPlayer.getId());
		assertEquals("Neymar", foundPlayer.getName());

	}
*/
}
