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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;

    @Column(nullable = false)
    private Integer restockRound;

    @Column(nullable = false)
    private TransferStatus transferStatus;

    @Column(nullable = false)
    private Long lastTransferUserId;


    public ProductNotificationHistory(Product product, int restockRound, TransferStatus transferStatus) {
        this.productId = product;
        this.restockRound = restockRound;
        this.transferStatus = transferStatus;
    }

    public void stopTransfer(Long lastTransferUserId, TransferStatus transferStatus) {
        this.lastTransferUserId = lastTransferUserId;
        this.transferStatus = transferStatus;
    }
}
