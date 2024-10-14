package com.sparta.companyassignment2.entity;

import com.sparta.companyassignment2.enums.TransferStatus;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productUserNotificationHistory")
@NoArgsConstructor
public class ProductUserNotificationHistory extends BaseTime{
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
    @Enumerated(EnumType.STRING)
    private TransferStatus transferStatus;

    public ProductUserNotificationHistory(Long productId, int restock, TransferStatus status, User user) {
        this.productId = productId;
        this.userId = user.getId();
        this.restockRound = restock;
        this.transferStatus = status;
    }
}
