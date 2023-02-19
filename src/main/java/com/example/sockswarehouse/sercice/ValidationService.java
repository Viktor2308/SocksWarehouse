package com.example.sockswarehouse.sercice;

import com.example.sockswarehouse.model.BoxOfSocks;
import com.example.sockswarehouse.model.Color;
import com.example.sockswarehouse.model.Size;

public interface ValidationService {
    boolean validate(BoxOfSocks boxOfSocks);
    boolean validate(Color color, Size size, int cottonMin, int cottonMax);
}
