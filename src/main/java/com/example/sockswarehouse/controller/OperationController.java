package com.example.sockswarehouse.controller;

import com.example.sockswarehouse.sercice.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/socks/operation")
@Tag(name = "API for work with operation files",
        description = "Import/export files of operation")
@RequiredArgsConstructor
public class OperationController {
    private final OperationService operationService;

    @GetMapping("/export")
    @Operation(summary = "Export json data file")
    public ResponseEntity<InputStreamResource> downloadSocksFile() {
        try {
            File dataFile = operationService.exportFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(dataFile));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(dataFile.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=" + dataFile.getName())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/import", consumes = "multipart/form-data")
    @Operation(summary = "Import json data file")
    public ResponseEntity<String> uploadSocksFile(@RequestParam MultipartFile file) {
        try {
            operationService.importFromFile(file);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error, file is not correct.");
        }
    }
}