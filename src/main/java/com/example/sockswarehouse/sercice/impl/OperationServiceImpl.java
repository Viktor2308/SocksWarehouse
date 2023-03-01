package com.example.sockswarehouse.sercice.impl;

import com.example.sockswarehouse.model.operation.Operation;
import com.example.sockswarehouse.model.operation.OperationType;
import com.example.sockswarehouse.model.socks.BoxOfSocks;
import com.example.sockswarehouse.repository.OperationRepository;
import com.example.sockswarehouse.sercice.FileService;
import com.example.sockswarehouse.sercice.OperationService;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor

public class OperationServiceImpl implements OperationService {

    private final FileService fileService;
    private final OperationRepository operationRepository;

    @Value("${path.to.operation.file}")
    private final Path path = Path.of("${path.to.data.file}", "operation.json");




    @Override
    public void arrive(BoxOfSocks boxOfSocks) {
        operationRepository.save(new Operation(OperationType.ARRIVAL, boxOfSocks));
    }

    @Override
    public void release(BoxOfSocks boxOfSocks) {
        operationRepository.save(new Operation(OperationType.RELEASE, boxOfSocks));
    }

    @Override
    public void writeOff(BoxOfSocks boxOfSocks) {
        operationRepository.save(new Operation(OperationType.WRITE_OFF, boxOfSocks));
    }

    @Override
    public File exportFile() throws IOException {

        return fileService.saveToFile(operationRepository.getList(), path).toFile();
    }

    @Override
    public void importFromFile(MultipartFile multipartFile) throws IOException {
        List<Operation> operationList = fileService.uploadFromFile
                (multipartFile, path, new TypeReference<>() {
                });
        operationRepository.replace(operationList);
    }
}
