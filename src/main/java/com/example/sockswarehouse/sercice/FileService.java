package com.example.sockswarehouse.sercice;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileService {


    <T> Path saveToFile(T content, Path path) throws IOException;

    <T> List<T> uploadFromFile(MultipartFile multipartFile,
                               Path path,
                               TypeReference<List<T>> typeReference) throws IOException;
}