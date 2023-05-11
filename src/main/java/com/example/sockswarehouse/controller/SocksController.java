package com.example.sockswarehouse.controller;

import com.example.sockswarehouse.model.socks.Socks;
import com.example.sockswarehouse.sercice.SocksService;
import com.example.sockswarehouse.util.LoggingUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/v1")
@Tag(name = "API for inventory of socks",
        description = "Registers the arrival of socks at the warehouse," +
                " the release of socks from the warehouse," +
                " the total number of socks in the warehouse and" +
                " the write-off of damaged (defective) socks.")
public class SocksController {

    private final SocksService socksService;

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<Void>> getAllTutorials(@RequestParam(required = false) String title) {
        return Mono.empty()
                .doOnEach(LoggingUtils.logOnComplete(x -> log.info("Test")))
                .map(x -> ok().build());
    }


    @Operation(summary = "Registers the arrival of socks at the warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Operation", content = @Content),
            @ApiResponse(responseCode = "409", description = "Resource already exists", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)})
    @PostMapping("/socks")
    public Mono<ResponseEntity<Void>> arrivalSocks(@RequestBody Socks socks,
                                                   ServerHttpRequest serverHttpRequest) {
        return Mono.empty()
                .doOnEach(LoggingUtils.logOnComplete(x -> log.info("Before arrival socks")))
                .then(socksService.save(socks))
                .doOnEach(LoggingUtils.logOnComplete(x -> log.info("Socks arrival")))
                .map(arrivalSocks -> ResponseEntity.created(
                        URI.create(serverHttpRequest.getPath().toString().concat("/")
                                .concat(socks.getId().toString()))).build());
    }

}

//    private final SocksService socksService;
//
//    public SocksController(SocksService socksService) {
//        this.socksService = socksService;
//    }
//
//
//    @PostMapping("/")
//    @Operation(summary = "Registers the arrival of socks at the warehouse.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Operation success"),
//            @ApiResponse(responseCode = "400", description = "Request parameters are missing or invalid"),
//            @ApiResponse(responseCode = "500", description = "Internal application error")})
//    public ResponseEntity<ResponseDto> arrivalOfSocks(@RequestBody BoxOfSocks boxOfSocks) {
//        socksService.arrivalOfSocks(boxOfSocks);
//        return ResponseEntity.ok(new ResponseDto(boxOfSocks.getQuantity() + " socks arrived at the warehouse"));
//    }
//
//
//    @PutMapping("/")
//    @Operation(summary = "Registers the release of socks from the warehouse.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Operation success"),
//            @ApiResponse(responseCode = "400", description = "Request parameters are missing or invalid"),
//            @ApiResponse(responseCode = "500", description = "Internal application error")})
//    public ResponseEntity<ResponseDto> releaseOfSocks(@RequestBody BoxOfSocks boxOfSocks) {
//        int releasedSocks = socksService.releaseOfSocks(boxOfSocks);
//        return ResponseEntity.ok(new ResponseDto(releasedSocks + " socks released at the warehouse"));
//    }
//
//    @GetMapping("/")
//    @Operation(summary = "Count of socks in the warehouse.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Operation success"),
//            @ApiResponse(responseCode = "400", description = "Request parameters are missing or invalid"),
//            @ApiResponse(responseCode = "500", description = "Internal application error")})
//    public ResponseEntity<ResponseDto> countOfSocks(@RequestParam Color color,
//                                                    @RequestParam Size size,
//                                                    @RequestParam int cottonMin,
//                                                    @RequestParam int cottonMax) {
//        int countOfSocks = socksService.countOfSocks(color, size, cottonMin, cottonMax);
//        return ResponseEntity.ok(new ResponseDto(countOfSocks + " socks in the warehouse"));
//    }
//
//    @DeleteMapping("/")
//    @Operation(summary = "Registers the write-off of damaged (defective) socks.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Operation success"),
//            @ApiResponse(responseCode = "400", description = "Request parameters are missing or invalid"),
//            @ApiResponse(responseCode = "500", description = "Internal application error")})
//    public ResponseEntity<ResponseDto> removeDefectiveSocks(@RequestBody BoxOfSocks boxOfSocks) {
//        int defectiveSocks = socksService.removeDefectiveSocks(boxOfSocks);
//        return ResponseEntity.ok(new ResponseDto(defectiveSocks + " the write-off from the warehouse"));
//    }
//}
