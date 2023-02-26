package com.example.sockswarehouse.sercice;

import com.example.sockswarehouse.model.socks.BoxOfSocks;
import com.example.sockswarehouse.model.socks.Color;
import com.example.sockswarehouse.model.socks.Size;

public interface ValidationService {
    /**
     * validate data
     *
     * @param boxOfSocks object
     * @return true if all data corrects
     */
    boolean validate(BoxOfSocks boxOfSocks);

    /**
     * validate data
     *
     * @param color     not null
     * @param size      not 0
     * @param cottonMin >=0, <=cottonMax
     * @param cottonMax <=100, =>cottonMin
     * @return true if all data corrects
     */
    boolean validate(Color color, Size size, int cottonMin, int cottonMax);
}
