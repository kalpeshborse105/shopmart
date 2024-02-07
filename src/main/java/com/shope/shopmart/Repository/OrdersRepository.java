package com.shope.shopmart.Repository;

import com.shope.shopmart.Entities.Orders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository  extends JpaRepository<Orders, Integer> {
}
