package com.example.sockswarehouse.sercice;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileService {

    /**
     * Save data to file
     *
     * @param content - collection to save data
     * @param path    - path to file
     * @param <T>     - type object
     * @return - path to file
     * @throws IOException - description
     */
    <T> Path saveToFile(T content, Path path) throws IOException;

    /**
     * Upload data from file
     *
     * @param multipartFile - upload file
     * @param path - path to file
     * @param typeReference - type object
     * @param <T> - type object
     * @return - path to file
     * @throws IOException - description
     */
    <T> List<T> uploadFromFile(MultipartFile multipartFile,
                               Path path,
                               TypeReference<List<T>> typeReference) throws IOException;
}
