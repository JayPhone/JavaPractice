package com.jayphone.practice.repository;

import com.jayphone.practice.entity.Account;

import java.util.List;

public interface AccountRepository {
    int save(Account account);

    int update(Account account);

    int deleteById(long id);

    List<Account> findAll();

    List<Account> findById(List<Long> idList);

    Account findByUsernameAndAge(String name, int age);

    List<Account> findAllByAccount(Account account);
}
