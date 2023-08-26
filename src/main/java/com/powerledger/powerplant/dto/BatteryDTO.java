package com.powerledger.powerplant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatteryDTO {
    private String name;
    private String postcode;
    private Integer capacity;
}
