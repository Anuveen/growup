package com.jpop.userservice.dao;


import com.jpop.userservice.model.UserTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserTable, Integer> {
}
