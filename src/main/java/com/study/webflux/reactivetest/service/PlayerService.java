package com.study.webflux.reactivetest.service;

import com.study.webflux.reactivetest.domain.Player;
import com.study.webflux.reactivetest.dto.PlayerDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PlayerService {

    Mono<Player> save(final PlayerDto playerDto);

    Mono<PlayerDto> find(UUID playerId);

    Mono<PlayerDto> find(String name, String nickname);

    Flux<PlayerDto> findAll();
}
