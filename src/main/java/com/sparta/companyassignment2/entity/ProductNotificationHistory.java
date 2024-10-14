package com.sparta.companyassignment2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productNotificationHistory")
@Getter
@NoArgsConstructor
public class ProductNotificationHistory extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long productId;

    @Column
    private Integer restockRound;

    @Column
    private Integer restockCnt;


    public ProductNotificationHistory(Long productId, int restockRound, int restockCnt) {
        this.productId = productId;
        this.restockRound = restockRound;
        this.restockCnt = restockCnt;
    }
}
