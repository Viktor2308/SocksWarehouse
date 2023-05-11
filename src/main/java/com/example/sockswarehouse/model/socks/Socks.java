package com.example.sockswarehouse.model.socks;

import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

/**
 * Socks
 */

@Table("socks")
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Socks extends AuditableEntity {

    private String color;
    private int size;
    private int cottonContent;
    private int stock;

    @Positive
    @EqualsAndHashCode.Exclude
    private BigDecimal price;

    @EqualsAndHashCode.Include
    private BigDecimal priceWithoutTrailingZeros() {
        return price != null ? price.stripTrailingZeros() : null;
    }
}

