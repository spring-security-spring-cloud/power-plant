package com.powerledger.powerplant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "batteries")
public class Battery extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String postcode;

    private Integer capacity;

    public Battery(String name, String postcode, int capacity) {
        this.name = name;
        this.postcode = postcode;
        this.capacity = capacity;
    }
}
