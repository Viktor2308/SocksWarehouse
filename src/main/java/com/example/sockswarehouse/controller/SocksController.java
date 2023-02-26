package com.example.sockswarehouse.controller;

import com.example.sockswarehouse.controller.dto.ResponseDto;
import com.example.sockswarehouse.model.socks.BoxOfSocks;
import com.example.sockswarehouse.model.socks.Color;
import com.example.sockswarehouse.model.socks.Size;
import com.example.sockswarehouse.sercice.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "API for inventory of socks",
        description = "Registers the arrival of socks at the warehouse," +
                " the release of socks from the warehouse," +
                " the total number of socks in the warehouse and" +
                " the write-off of damaged (defective) socks.")

public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }


    @PostMapping("/")
    @Operation(summary = "Registers the arrival of socks at the warehouse.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation success"),
            @ApiResponse(responseCode = "400", description = "Request parameters are missing or invalid"),
            @ApiResponse(responseCode = "500", description = "Internal application error")})
    public ResponseEntity<ResponseDto> arrivalOfSocks(@RequestBody BoxOfSocks boxOfSocks) {
        socksService.arrivalOfSocks(boxOfSocks);
        return ResponseEntity.ok(new ResponseDto(boxOfSocks.getQuantity() + " socks arrived at the warehouse"));
    }


    @PutMapping("/")
    @Operation(summary = "Registers the release of socks from the warehouse.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation success"),
            @ApiResponse(responseCode = "400", description = "Request parameters are missing or invalid"),
            @ApiResponse(responseCode = "500", description = "Internal application error")})
    public ResponseEntity<ResponseDto> releaseOfSocks(@RequestBody BoxOfSocks boxOfSocks) {
        int releasedSocks = socksService.releaseOfSocks(boxOfSocks);
        return ResponseEntity.ok(new ResponseDto(releasedSocks + " socks released at the warehouse"));
    }

    @GetMapping("/")
    @Operation(summary = "Count of socks in the warehouse.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation success"),
            @ApiResponse(responseCode = "400", description = "Request parameters are missing or invalid"),
            @ApiResponse(responseCode = "500", description = "Internal application error")})
    public ResponseEntity<ResponseDto> countOfSocks(@RequestParam Color color,
                                                    @RequestParam Size size,
                                                    @RequestParam int cottonMin,
                                                    @RequestParam int cottonMax) {
        int countOfSocks = socksService.countOfSocks(color, size, cottonMin, cottonMax);
        return ResponseEntity.ok(new ResponseDto(countOfSocks + " socks in the warehouse"));
    }

    @DeleteMapping("/")
    @Operation(summary = "Registers the write-off of damaged (defective) socks.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation success"),
            @ApiResponse(responseCode = "400", description = "Request parameters are missing or invalid"),
            @ApiResponse(responseCode = "500", description = "Internal application error")})
    public ResponseEntity<ResponseDto> removeDefectiveSocks(@RequestBody BoxOfSocks boxOfSocks) {
        int defectiveSocks = socksService.removeDefectiveSocks(boxOfSocks);
        return ResponseEntity.ok(new ResponseDto(defectiveSocks + " the write-off from the warehouse"));
    }
}
