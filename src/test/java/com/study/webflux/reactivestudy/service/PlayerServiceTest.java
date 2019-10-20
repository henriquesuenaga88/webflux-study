package com.study.webflux.reactivestudy.service;

import com.study.webflux.reactivestudy.domain.Player;
import com.study.webflux.reactivestudy.dto.PlayerDto;
import com.study.webflux.reactivestudy.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Flux.just;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    private static final UUID PLAYER_ID = UUID.randomUUID();
    private static final String PLAYER_NAME = "Player1";
    private static final String PLAYER_NICKNAME = "Pl1";
    private static final int PLAYER_AGE = 10;
    private PlayerService service;

    @Mock
    private PlayerRepository repository;

    @BeforeEach
    void setUp() {
        service = new PlayerServiceImpl(repository);
    }


    @Test
    void saveSuccess() {
        when(repository.save(Mockito.any()))
                .thenReturn(Mono.just(getPlayer()));
        Mono<Player> playerMono = service.save(PlayerDto.builder().build());

        assertThat(playerMono, notNullValue());
        Player player = playerMono.block();
        assertThat(player.getName(), is(PLAYER_NAME));
        assertThat(player.getNickname(), is(PLAYER_NICKNAME));
        assertThat(player.getAge(), is(PLAYER_AGE));
    }

    @Test
    void findByIdSuccess() {
        when(repository.findById(PLAYER_ID)).thenReturn(Mono.just(getPlayer()));

        final Mono<PlayerDto> player = service.find(PLAYER_ID);

        assertThat(player, notNullValue());
        PlayerDto playerDto = player.block();
        assertThat(playerDto.getName(), is(PLAYER_NAME));
        assertThat(playerDto.getNickname(), is(PLAYER_NICKNAME));
        assertThat(playerDto.getAge(), is(PLAYER_AGE));
    }


    @Test
    void findByNameAndNicknameSuccess() {
        when(repository.findByNameAndNickname(PLAYER_NAME, PLAYER_NICKNAME)).thenReturn(Mono.just(getPlayer()));

        final Mono<PlayerDto> player = service.find(PLAYER_NAME, PLAYER_NICKNAME);

        assertThat(player, notNullValue());
        PlayerDto playerDto = player.block();
        assertThat(playerDto.getName(), is(PLAYER_NAME));
        assertThat(playerDto.getNickname(), is(PLAYER_NICKNAME));
        assertThat(playerDto.getAge(), is(PLAYER_AGE));
    }

    @Test
    void findAllSuccess() {
        when(repository.findAll()).thenReturn(just(getPlayer()));

        final Flux<PlayerDto> all = service.findAll();

        assertThat(all, notNullValue());
        PlayerDto playerDto = all.blockFirst();
        assertThat(playerDto.getName(), is(PLAYER_NAME));
        assertThat(playerDto.getNickname(), is(PLAYER_NICKNAME));
        assertThat(playerDto.getAge(), is(PLAYER_AGE));
    }


    private Player getPlayer() {
        return Player.builder()
                .id(PLAYER_ID)
                .name(PLAYER_NAME)
                .nickname(PLAYER_NICKNAME)
                .age(PLAYER_AGE).build();
    }

}
