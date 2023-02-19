package com.example.sockswarehouse.sercice.impl;

import com.example.sockswarehouse.exception.ValidationException;
import com.example.sockswarehouse.model.BoxOfSocks;
import com.example.sockswarehouse.model.Color;
import com.example.sockswarehouse.model.Size;
import com.example.sockswarehouse.model.Socks;
import com.example.sockswarehouse.repository.SocksRepository;
import com.example.sockswarehouse.sercice.SocksService;
import com.example.sockswarehouse.sercice.ValidationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service

public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;
    private final ValidationService validationService;

    public SocksServiceImpl(SocksRepository socksRepository, ValidationService validationService) {
        this.socksRepository = socksRepository;
        this.validationService = validationService;
    }

    @Override
    public void arrivalOfSocks(BoxOfSocks boxOfSocks) {
        if (!validationService.validate(boxOfSocks)) {
            throw new ValidationException();
        }
        socksRepository.save(boxOfSocks);
    }

    @Override
    public int releaseOfSocks(BoxOfSocks boxOfSocks) {
        if (!validationService.validate(boxOfSocks)) {
            throw new ValidationException();
        }
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
        return socksRepository.remove(boxOfSocks);
    }
}
