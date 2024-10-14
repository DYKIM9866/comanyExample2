package com.sparta.companyassignment2.entity;

import com.sparta.companyassignment2.enums.TransferStatus;
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


    public ProductUserNotificationHistory(Long productId, int restock, TransferStatus status, User user) {
        this.productId = productId;
        this.userId = user.getId();
        this.restockRound = restock;
    }
}
