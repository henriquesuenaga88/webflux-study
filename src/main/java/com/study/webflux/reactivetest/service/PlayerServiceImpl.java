package com.study.webflux.reactivetest.service;

import com.study.webflux.reactivetest.domain.Player;
import com.study.webflux.reactivetest.dto.PlayerDto;
import com.study.webflux.reactivetest.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository repository;

    public PlayerServiceImpl(PlayerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Player> save(final PlayerDto playerDto) {
        return repository.save(Player.builder()
                .id(UUID.randomUUID())
                .age(playerDto.getAge())
                .name(playerDto.getName())
                .nickname(playerDto.getNickname())
                .build());
    }

    @Override
    public Mono<PlayerDto> find(UUID playerId) {
        return repository.findById(playerId)
                .map(player ->
                        PlayerDto.builder()
                                .name(player.getName())
                                .nickname(player.getNickname())
                                .age(player.getAge())
                                .build()
                );
    }

    @Override
    public Mono<PlayerDto> find(String name, String nickname) {
        return repository.findByNameAndNickname(name, nickname)
                .map(player -> PlayerDto.builder()
                        .name(player.getName())
                        .nickname(player.getNickname())
                        .age(player.getAge())
                        .build());
    }

    @Override
    public Flux<PlayerDto> findAll() {
        return repository.findAll()
                .map(player -> PlayerDto.builder()
                        .name(player.getName())
                        .nickname(player.getNickname())
                        .age(player.getAge())
                        .build());
    }
}
