package com.example.sockswarehouse.repository;

import com.example.sockswarehouse.model.socks.BoxOfSocks;
import com.example.sockswarehouse.model.socks.Socks;

import java.util.List;
import java.util.Map;

public interface SocksRepository {
    /**
     * Save new socks
     *
     * @param boxOfSocks new socks
     */
    void save(BoxOfSocks boxOfSocks);

    /**
     * Remove socks from warehouse
     *
     * @param boxOfSocks socks for remove
     * @return count remove socks
     */
    int remove(BoxOfSocks boxOfSocks);

    /**
     * Get MAP socks from warehouse
     *
     * @return MAP socks
     */
    Map<Socks, Integer> getCountAllSocks();

    /**
     * Get list of socks from warehouse for save to file
     *
     * @return List of socks
     */
    List<BoxOfSocks> getList();

    /**
     * Replase list of socks from warehouse on list from file
     */
    void replace(List<BoxOfSocks> boxOfSocks);
}
