package com.jayphone.practice.repository;

import com.jayphone.practice.entity.Classes;

public interface ClassesRepository {
    Classes findById(long id);
}
