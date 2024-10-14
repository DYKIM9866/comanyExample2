package com.sparta.companyassignment2.repository;

import com.sparta.companyassignment2.entity.ProductUserNotificationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductUserNotificationHistoryRepository
        extends JpaRepository<ProductUserNotificationHistory, Long> {
}
