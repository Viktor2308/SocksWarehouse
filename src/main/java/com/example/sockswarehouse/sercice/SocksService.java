package com.example.sockswarehouse.sercice;

import com.example.sockswarehouse.model.socks.BoxOfSocks;
import com.example.sockswarehouse.model.socks.Color;
import com.example.sockswarehouse.model.socks.Size;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface SocksService {
    /**
     * arrival of socks at the warehouse
     *
     * @param boxOfSocks arrival of socks
     */
    void arrivalOfSocks(BoxOfSocks boxOfSocks);

    /**
     * release of socks from the warehouse
     *
     * @param boxOfSocks count socks fo release
     * @return socks release in fact
     */
    int releaseOfSocks(BoxOfSocks boxOfSocks);

    /**
     * count of socks in the warehouse
     *
     * @param color     socks
     * @param size      socks
     * @param cottonMin socks
     * @param cottonMax socks
     * @return count of socks
     */
    int countOfSocks(Color color, Size size, int cottonMin, int cottonMax);

    /**
     * write-off of damaged (defective) socks
     *
     * @param boxOfSocks damaged (defective) socks
     * @return socks write-off in fact
     */
    int removeDefectiveSocks(BoxOfSocks boxOfSocks);

    File exportFile() throws IOException;

    void importFromFile(MultipartFile multipartFile) throws IOException;

}
