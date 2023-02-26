package com.example.sockswarehouse.sercice;

import com.example.sockswarehouse.model.socks.BoxOfSocks;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface OperationService {
    /**
     * Create operation arrive
     *
     * @param boxOfSocks object
     */
    void arrive(BoxOfSocks boxOfSocks);

    /**
     * Create operation release
     *
     * @param boxOfSocks object
     */
    void release(BoxOfSocks boxOfSocks);

    /**
     * Create operation writeOff
     *
     * @param boxOfSocks object
     */
    void writeOff(BoxOfSocks boxOfSocks);

    /**
     * Export operation data to a file
     *
     * @return file.json
     * @throws IOException description
     */
    File exportFile() throws IOException;

    /**
     * Import operation data from file
     *
     * @throws IOException description
     */
    void importFromFile(MultipartFile multipartFile) throws IOException;
}
