package com.jayphone.practice.repository;

import com.jayphone.practice.entity.Customer;

public interface CustomerRepository {
    Customer findById(long id);
}
