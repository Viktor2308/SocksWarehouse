package com.example.sockswarehouse.sercice;

import com.example.sockswarehouse.model.socks.BoxOfSocks;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface OperationService {
    void arrive(BoxOfSocks boxOfSocks);

    void release(BoxOfSocks boxOfSocks);

    void writeOff(BoxOfSocks boxOfSocks);

    File exportFile() throws IOException;

    void importFromFile(MultipartFile multipartFile) throws IOException;
}
