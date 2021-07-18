package com.example.exercise.repositories;

import com.example.exercise.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.productsSold.size > 0 " +
            "order by u.lastName, u.firstName")
    List<User> findAllByCountOfSalesMoreThanOne();
}
