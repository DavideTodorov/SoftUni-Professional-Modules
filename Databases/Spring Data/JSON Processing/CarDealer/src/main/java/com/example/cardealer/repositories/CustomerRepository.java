package com.example.cardealer.repositories;

import com.example.cardealer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select c.name, count(s.id) as carsBought, sum(p.price)\n" +
            "from customers as c\n" +
            "join sales s on c.id = s.customer_id\n" +
            "join cars_parts cp on s.car_id = cp.car_id\n" +
            "join parts p on p.id = cp.parts_id\n" +
            "group by c.id\n" +
            "having carsBought > 1;", nativeQuery = true)
    List<String> findAllByCarsBoughtMoreThanOne();
}
