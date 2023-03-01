package com.example.sockswarehouse.controller;

import com.example.sockswarehouse.sercice.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/socks/file")
@Tag(name = "API for work with files",
        description = "Import/export files of socks")
@RequiredArgsConstructor
public class FileController {
    private final SocksService socksService;

    @GetMapping("/export")
    @Operation(summary = "Export json data file")
    public ResponseEntity<InputStreamResource> downloadSocksFile() {
        try {
            File dataFile = socksService.exportFile();
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

    @PostMapping(value = "/import", consumes = "multipart/form-data")
    @Operation(summary = "Import json data file")
    public ResponseEntity<String> uploadSocksFile(@RequestParam MultipartFile file) {
        try {
            socksService.importFromFile(file);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error, file is not correct.");
        }
    }
}
