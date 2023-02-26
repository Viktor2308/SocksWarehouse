package com.example.sockswarehouse.model.operation;

import com.example.sockswarehouse.model.socks.BoxOfSocks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    private OperationType operationType;
    private BoxOfSocks boxOfSocks;
    private final LocalDateTime localDateTime = LocalDateTime.now();
}
