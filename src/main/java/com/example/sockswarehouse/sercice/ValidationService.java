package com.example.sockswarehouse.sercice;

import com.example.sockswarehouse.model.socks.BoxOfSocks;
import com.example.sockswarehouse.model.socks.Color;
import com.example.sockswarehouse.model.socks.Size;

public interface ValidationService {
    boolean validate(BoxOfSocks boxOfSocks);
    boolean validate(Color color, Size size, int cottonMin, int cottonMax);
}
