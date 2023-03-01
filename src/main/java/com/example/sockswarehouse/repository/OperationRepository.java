package com.example.sockswarehouse.repository;

import com.example.sockswarehouse.model.operation.Operation;

import java.util.List;

public interface OperationRepository {
    /**
     * Save operation
     *
     * @param operation with socks
     */
    void save(Operation operation);

    /**
     * Get all save operation
     *
     * @return collection operation List
     */
    List<Operation> getList();

    /**
     * Replace data from upload file
     *
     * @param operation collection from upload file
     */
    void replace(List<Operation> operation);
}
