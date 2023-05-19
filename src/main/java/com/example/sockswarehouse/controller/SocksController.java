package com.example.sockswarehouse.controller;

import com.example.sockswarehouse.model.socks.Socks;
import com.example.sockswarehouse.sercice.SocksService;
import com.example.sockswarehouse.util.LoggingUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/")
@Tag(name = "API for inventory of socks",
        description = "Registers the arrival of socks at the warehouse," +
                " the release of socks from the warehouse," +
                " the total number of socks in the warehouse and" +
                " the write-off of damaged (defective) socks.")
public class SocksController {

    private final SocksService socksService;

    @Operation(summary = "Registers the arrival of socks at the warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Operation", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Socks.class))),
            @ApiResponse(responseCode = "409", description = "Resource already exists", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @PostMapping("/v1/socks")
    public Mono<ResponseEntity<Void>> arrivalSocks(@RequestBody Socks socks,
                                                   ServerHttpRequest serverHttpRequest) {
        return Mono.empty()
                .doOnEach(LoggingUtils.logOnComplete(x -> log.info("Before arrival socks")))
                .then(socksService.save(socks))
                .doOnEach(LoggingUtils.logOnComplete(x -> log.info("Socks arrival")))
                .map(arrivalSocks -> ResponseEntity.created(
                        URI.create(serverHttpRequest.getPath().toString().concat("/")
                                .concat(Objects.requireNonNull(socks.getId()).toString()))).build());
    }

    @Operation(summary = "Get Socks by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Socks.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @GetMapping("/v1/socks/{id}")
    public Mono<Socks> getSocksById(@PathVariable UUID id) {
        return Mono.empty()
                .doOnEach(LoggingUtils.logOnComplete(x -> log.info("Before socks obtained")))
                .then(socksService.findById(id))
                .doOnEach(LoggingUtils.logOnComplete(x -> log.info("Socks obtained")));
    }

    @Operation(summary = "Get all Socks in warehous", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Socks.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @GetMapping("/v1/socks")
    public Flux<Socks> getAllSocks() {
        return Flux.empty()
                .doOnEach(LoggingUtils.logOnComplete(x -> log.info("Before socks obtained")))
                .thenMany(socksService.findAll())
                .doOnEach(LoggingUtils.logOnComplete(x -> log.info("Socks obtained")));
    }

    @Operation(summary = "Update Socks", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @PutMapping("/v1/socks/{id}")
    public Mono<ResponseEntity<Void>> updateSocks(@PathVariable("id") UUID id,
                                                    @RequestBody Socks socks) {
        return Mono.empty()
                .doOnEach(LoggingUtils.logOnComplete(x -> log.info("Before updating socks")))
                .then(socksService.update(id, socks))
                .doOnEach(LoggingUtils.logOnComplete(x -> log.info("Socks updated")))
                .map(x -> ok().build());
    }

    @Operation(summary = "Delete socks", responses = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @DeleteMapping("/v1/socks/{id}")
    public Mono<ResponseEntity<Void>> deleteSocks(@PathVariable("id") UUID id) {
        return Mono.empty()
                .doOnEach(LoggingUtils.logOnComplete(x -> log.info("Before deleting socks")))
                .then(socksService.deleteSocksById(id))
                .doOnEach(LoggingUtils.logOnComplete(x -> log.info("Socks deleted")))
                .map(x -> ok().build());
    }
}

