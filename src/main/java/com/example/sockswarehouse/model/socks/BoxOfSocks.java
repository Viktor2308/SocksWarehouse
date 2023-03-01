package com.example.sockswarehouse.model.socks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Supply of socks to the warehouse
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxOfSocks {
    private Socks socks;
    private int quantity;


}
