package com.jayphone.practice.repository;

import com.jayphone.practice.entity.Student;

public interface StudentRepository {
    Student findById(long id);
}
