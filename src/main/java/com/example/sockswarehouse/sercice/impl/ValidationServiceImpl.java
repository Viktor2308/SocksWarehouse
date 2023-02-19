package com.example.sockswarehouse.sercice.impl;

import com.example.sockswarehouse.model.BoxOfSocks;
import com.example.sockswarehouse.model.Color;
import com.example.sockswarehouse.model.Size;
import com.example.sockswarehouse.sercice.ValidationService;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(BoxOfSocks boxOfSocks) {
        return boxOfSocks.getSocks() != null &&
                boxOfSocks.getQuantity() > 0 &&
                boxOfSocks.getSocks().getColor() != null &&
                boxOfSocks.getSocks().getSize() != null &&
                checkCotton(boxOfSocks.getSocks().getCottonContent(), boxOfSocks.getSocks().getCottonContent());
    }

    @Override
    public boolean validate(Color color, Size size, int cottonMin, int cottonMax) {
        return color != null &&
                size != null &&
                cottonMin >= 0 &&
                cottonMax <= 100;
    }

    private boolean checkCotton(int min, int max) {
        return min >= 0 && max <= 100;
    }
}
