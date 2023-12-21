package com.springboot.bakery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bakery.model.Order;

public interface BakeryRepository extends JpaRepository<Order,Long>{

}
