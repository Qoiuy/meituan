package com.cinsc.meituan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Property {
    @Id
    private long propertyId;
    private long dishId;
    private long propertyName;
    private String propertyValue;
}
