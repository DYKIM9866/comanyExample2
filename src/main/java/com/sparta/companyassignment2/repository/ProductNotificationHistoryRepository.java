package com.sparta.companyassignment2.repository;

import com.sparta.companyassignment2.entity.Product;
import com.sparta.companyassignment2.entity.ProductNotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductNotificationHistoryRepository extends JpaRepository<ProductNotificationHistory, Long> {
    Integer findTopByProductIdOrderByRestockRoundDesc(Product product);
}
