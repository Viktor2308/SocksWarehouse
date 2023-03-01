package com.example.sockswarehouse.sercice.impl;

import com.example.sockswarehouse.exception.ValidationException;
import com.example.sockswarehouse.model.socks.BoxOfSocks;
import com.example.sockswarehouse.model.socks.Color;
import com.example.sockswarehouse.model.socks.Size;
import com.example.sockswarehouse.model.socks.Socks;
import com.example.sockswarehouse.repository.SocksRepository;
import com.example.sockswarehouse.sercice.FileService;
import com.example.sockswarehouse.sercice.OperationService;
import com.example.sockswarehouse.sercice.SocksService;
import com.example.sockswarehouse.sercice.ValidationService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;
    private final ValidationService validationService;
    private final FileService fileService;
    private final OperationService operationService;
    @Value("${path.to.data.file}")
    private final Path path = Path.of("${path.to.data.file}", "socks.json");


    @Override
    public void arrivalOfSocks(BoxOfSocks boxOfSocks) {
        if (!validationService.validate(boxOfSocks)) {
            throw new ValidationException();
        }
        operationService.arrive(boxOfSocks);
        socksRepository.save(boxOfSocks);
    }

    @Override
    public int releaseOfSocks(BoxOfSocks boxOfSocks) {
        if (!validationService.validate(boxOfSocks)) {
            throw new ValidationException();
        }
        operationService.release(boxOfSocks);
        return socksRepository.remove(boxOfSocks);
    }

    @Override
    public int countOfSocks(Color color, Size size, int cottonMin, int cottonMax) {
        if (!validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new ValidationException();
        }
        for (Map.Entry<Socks, Integer> entry : socksRepository.getCountAllSocks().entrySet()) {
            if (entry.getKey().getColor().equals(color) &&
                    entry.getKey().getSize().equals(size) &&
                    entry.getKey().getCottonContent() >= cottonMin &&
                    entry.getKey().getCottonContent() <= cottonMax) {
                return entry.getValue();
            }
        }
        return 0;
    }

    @Override
    public int removeDefectiveSocks(BoxOfSocks boxOfSocks) {
        if (!validationService.validate(boxOfSocks)) {
            throw new ValidationException();
        }
        operationService.writeOff(boxOfSocks);
        return socksRepository.remove(boxOfSocks);
    }

    @Override
    public File exportFile() throws IOException {
        return fileService.saveToFile(socksRepository.getList(), path).toFile();
    }

    @Override
    public void importFromFile(MultipartFile multipartFile) throws IOException {
        List<BoxOfSocks> boxOfSocksList = fileService.uploadFromFile
                (multipartFile, path, new TypeReference<List<BoxOfSocks>>() {
                });
        socksRepository.replace(boxOfSocksList);
    }
}
