package com.jayphone.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Goods {
    private long id;
    private String name;
    private List<Customer> customers;
}
