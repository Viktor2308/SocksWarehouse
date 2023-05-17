package com.example.sockswarehouse.repository;

import com.example.sockswarehouse.model.socks.Socks;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface SocksRepositoryR2bdc extends R2dbcRepository<Socks, UUID> {

    @Override
    Mono<Socks> findById(UUID id);

    @Override
    Mono<Socks> save(Socks socks);

    @Override
    Flux<Socks> findAll();

    @Modifying
    @Query("delete from socks where id = :id")
    Mono<Integer> deleteSocksById(UUID id);
}
