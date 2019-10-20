package com.study.webflux.reactivestudy.api;

import com.study.webflux.reactivestudy.domain.Player;
import com.study.webflux.reactivestudy.dto.PlayerDto;
import com.study.webflux.reactivestudy.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/player")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Mono<Player>> saveController(@RequestBody PlayerDto playerDTO) {
        log.info("request : {}", playerDTO);
        final Mono<Player> response = service.save(playerDTO)
                .doOnSuccess(player -> log.info("Response: {}", player))
                .doOnError(error -> log.error("Unexpected error: {}", error));
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping(value = "/{playerId}")
    public ResponseEntity<Mono<PlayerDto>> getPlayer(@PathVariable UUID playerId) {
        final Mono<PlayerDto> response = service.find(playerId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Flux<PlayerDto>> getAllPlayers() {
        final Flux<PlayerDto> players = service.findAll();
        return ResponseEntity.ok(players);
    }

}
