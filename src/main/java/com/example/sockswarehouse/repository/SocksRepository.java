package com.example.sockswarehouse.repository;

import com.example.sockswarehouse.model.BoxOfSocks;
import com.example.sockswarehouse.model.Socks;

import java.util.Map;

public interface SocksRepository {

    void save(BoxOfSocks boxOfSocks);

    int remove(BoxOfSocks boxOfSocks);

    Map<Socks, Integer> getCountAllSocks();
}
