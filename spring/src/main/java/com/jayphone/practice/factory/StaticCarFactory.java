package com.jayphone.practice.factory;

import com.jayphone.practice.entity.Car;

import java.util.HashMap;
import java.util.Map;

public class StaticCarFactory {
    private static Map<Long, Car> carMap;

    static {
        carMap = new HashMap<>();
        carMap.put(1L, new Car(1L, "宝马"));
        carMap.put(2L, new Car(2L, "奔驰"));
    }

    public static Car getCar(long id) {
        return carMap.get(id);
    }
}
