package com.jayphone.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Integer age;
}
