package com.example.sockswarehouse.sercice;

import com.example.sockswarehouse.model.socks.Socks;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SocksService {
    Mono<Socks> findById(UUID id);
    Mono<Socks> save(Socks socks);
    Mono<Socks> update(UUID id, Socks socks);
    Mono<Integer> deleteSocksById(UUID id);
    Flux<Socks> findAll();
    Mono<Void> deleteAll();
}
