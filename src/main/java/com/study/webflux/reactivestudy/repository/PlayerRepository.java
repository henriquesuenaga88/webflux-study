package com.study.webflux.reactivestudy.repository;


import com.study.webflux.reactivestudy.domain.Player;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface PlayerRepository extends ReactiveMongoRepository<Player, UUID> {

    @Query("{ 'name': ?0, 'nickname': ?1}")
    Mono<Player> findByNameAndNickname(String name, String nickname);

}
