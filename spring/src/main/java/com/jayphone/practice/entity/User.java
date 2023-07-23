package com.jayphone.practice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class User {
    private int id;
    private int name;

    public User() {
        System.out.println("User创建");
    }
}
