package com.sparta.companyassignment2.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "productUserNotificationHistory")
@NoArgsConstructor
public class ProductUserNotificationHistory extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long productId;

    @Column
    private Long userId;

    @Column
    private Integer restockRound;

    @Column
    private LocalDateTime transferDateTime;


    public ProductUserNotificationHistory(Long productId, Long userId, Integer restockRound, LocalDateTime now) {
        this.productId = productId;
        this.userId = userId;
        this.restockRound = restockRound;
        this.transferDateTime = now;
    }
}
