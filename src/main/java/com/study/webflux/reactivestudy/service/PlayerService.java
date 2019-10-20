package com.study.webflux.reactivestudy.service;

import com.study.webflux.reactivestudy.domain.Player;
import com.study.webflux.reactivestudy.dto.PlayerDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PlayerService {

    Mono<Player> save(final PlayerDto playerDto);

    Mono<PlayerDto> find(UUID playerId);

    Mono<PlayerDto> find(String name, String nickname);

    Flux<PlayerDto> findAll();
}
