package com.example.sockswarehouse.sercice.impl;

import com.example.sockswarehouse.sercice.FileService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final ObjectMapper objectMapper;

    @Override
    public <T> Path saveToFile(T content, Path path) throws IOException {
        String json = objectMapper.writeValueAsString(content);
        createNewFile(path);
        return Files.writeString(path, json);
    }

    @Override
    public <T> List<T> uploadFromFile(MultipartFile multipartFile,
                                      Path path,
                                      TypeReference<List<T>> typeReference) throws IOException {
        uploadFile(multipartFile, path);
        return readListFromFile(path, typeReference);
    }

    private <T> List<T> readListFromFile(Path path, TypeReference<List<T>> typeReference) throws IOException {
        String json = Files.readString(path);
        if (json.isEmpty()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(json, typeReference);
    }

    private void uploadFile(MultipartFile multipartFile, Path path) throws IOException {
        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
        try (
                InputStream inputStream = multipartFile.getInputStream();
                OutputStream outputStream = Files.newOutputStream(path, CREATE_NEW);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1024)
        ) {
            bufferedInputStream.transferTo(bufferedOutputStream);
        }

    }

    private void createNewFile(Path path) throws IOException {
        Files.deleteIfExists(path);
        Files.createFile(path);
    }

}
