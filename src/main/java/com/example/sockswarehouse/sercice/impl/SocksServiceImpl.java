package com.example.sockswarehouse.sercice.impl;

import com.example.sockswarehouse.exception.Conflict;
import com.example.sockswarehouse.model.socks.Socks;
import com.example.sockswarehouse.repository.SocksRepositoryR2bdc;
import com.example.sockswarehouse.sercice.SocksService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class SocksServiceImpl implements SocksService {

    private SocksRepositoryR2bdc socksRepository;

    @Override
    public Flux<Socks> findAll() {
        return socksRepository.findAll();
    }

    @Override
    public Mono<Socks> findById(UUID id) {
        return socksRepository.findById(id);
    }


    @Transactional
    @Override
    public Mono<Socks> save(Socks socks) {
        return socksRepository.save(socks).doOnError(DataIntegrityViolationException.class, e -> {
            throw new Conflict("ERROR PRODUCT, ALREADY EXISTS " + ObjectUtils.nullSafeToString(socks.getId()));
        });
    }

    @Override
    public Mono<Socks> update(UUID id, Socks socks) {
        return socksRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalTutorial -> {
                    if (optionalTutorial.isPresent()) {
                        socks.setId(id);
                        socks.setStock(socks.getStock() + optionalTutorial.get().getStock());
                        return socksRepository.save(socks);
                    }

                    return Mono.empty();
                });
    }

    @Override
    public Mono<Integer> deleteSocksById(UUID id) {
        return socksRepository.deleteSocksById(id);
    }

    @Override
    public Mono<Void> deleteAll() {
        return socksRepository.deleteAll();
    }
}
