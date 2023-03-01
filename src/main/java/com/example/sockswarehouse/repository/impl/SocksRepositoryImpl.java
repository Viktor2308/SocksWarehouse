package com.example.sockswarehouse.repository.impl;

import com.example.sockswarehouse.model.socks.BoxOfSocks;
import com.example.sockswarehouse.model.socks.Socks;
import com.example.sockswarehouse.repository.SocksRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SocksRepositoryImpl implements SocksRepository {
    private final HashMap<Socks, Integer> socksHashMap = new HashMap<>();

    @Override
    public void save(BoxOfSocks boxOfSocks) {
        if (socksHashMap.containsKey(boxOfSocks.getSocks())) {
            socksHashMap.replace(boxOfSocks.getSocks(),
                    socksHashMap.get(boxOfSocks.getSocks()) + boxOfSocks.getQuantity());
        } else {
            socksHashMap.put(boxOfSocks.getSocks(), boxOfSocks.getQuantity());
        }

    }

    @Override
    public int remove(BoxOfSocks boxOfSocks) {
        if (socksHashMap.containsKey(boxOfSocks.getSocks())) {
            if (socksHashMap.get(boxOfSocks.getSocks()) > boxOfSocks.getQuantity()) {
                socksHashMap.replace(boxOfSocks.getSocks(),
                        socksHashMap.get(boxOfSocks.getSocks()) - boxOfSocks.getQuantity());
                return boxOfSocks.getQuantity();
            } else {
                int removeSocks = socksHashMap.get(boxOfSocks.getSocks());
                socksHashMap.remove(boxOfSocks.getSocks());
                return removeSocks;
            }
        } else {
            return 0;
        }
    }

    @Override
    public Map<Socks, Integer> getCountAllSocks() {
        return socksHashMap;
    }

    @Override
    public List<BoxOfSocks> getList() {
        List<BoxOfSocks> boxOfSocksArrayList = new ArrayList<>();
        for(Map.Entry<Socks,Integer> entry : socksHashMap.entrySet()){
            boxOfSocksArrayList.add(new BoxOfSocks(entry.getKey(), entry.getValue()));
        }
        return boxOfSocksArrayList;
    }

    @Override
    public void replace(List<BoxOfSocks> boxOfSocks) {
        socksHashMap.clear();
        for (BoxOfSocks box : boxOfSocks) {
            save(box);
        }
    }

}
