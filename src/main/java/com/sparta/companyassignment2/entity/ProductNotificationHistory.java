package com.sparta.companyassignment2.entity;

import com.sparta.companyassignment2.enums.TransferStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productNotificationHistory")
@Getter
@NoArgsConstructor
public class ProductNotificationHistory extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer restockRound;

    @Column(nullable = false)
    private TransferStatus transferStatus;

    @Column(nullable = false)
    private Long lastTransferUserId;


    public ProductNotificationHistory(Long productId, int restockRound, int restockCnt) {
        this.productId = productId;
        this.restockRound = restockRound;
    }
}
