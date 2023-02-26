package com.example.sockswarehouse.repository;

import com.example.sockswarehouse.model.socks.BoxOfSocks;
import com.example.sockswarehouse.model.socks.Socks;

import java.util.List;
import java.util.Map;

public interface SocksRepository {

    void save(BoxOfSocks boxOfSocks);

    int remove(BoxOfSocks boxOfSocks);

    Map<Socks, Integer> getCountAllSocks();

    List<BoxOfSocks> getList();

    void replace(List<BoxOfSocks>boxOfSocks);
}
