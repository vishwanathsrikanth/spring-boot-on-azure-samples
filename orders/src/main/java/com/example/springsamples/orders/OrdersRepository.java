package com.example.springsamples.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<OrderModel, Long> {
    Optional<List<OrderModel>> findByUserId(Long user_id);
}
