package com.jayphone.practice.repository;

import com.jayphone.practice.entity.Goods;

public interface GoodsRepository {
    Goods findById(long id);
}
