package com.example.sockswarehouse.repository.impl;

import com.example.sockswarehouse.model.operation.Operation;
import com.example.sockswarehouse.repository.OperationRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OperationRepositoryImpl implements OperationRepository {
    private final List<Operation> operationArrayList = new ArrayList<>();

    @Override
    public void save(Operation operation) {
        operationArrayList.add(operation);
    }

    @Override
    public List<Operation> getList() {
        return operationArrayList;
    }

    @Override
    public void replace(List<Operation> operation) {
        operationArrayList.clear();
        for (Operation operationNew : operation) {
            save(operationNew);
        }
    }
}
