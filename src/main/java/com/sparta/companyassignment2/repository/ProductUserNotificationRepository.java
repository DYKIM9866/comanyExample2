package com.sparta.companyassignment2.repository;

import com.sparta.companyassignment2.entity.ProductUserNotification;
import com.sparta.companyassignment2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductUserNotificationRepository extends JpaRepository<ProductUserNotification,Long> {
    @Query("select pun.user From ProductUserNotification pun where pun.product.id = :productId order by pun.createdAt ASC")
    List<User> findUserByProductIdOrderByCreatedAt(@Param("productId") Long productId);
}
