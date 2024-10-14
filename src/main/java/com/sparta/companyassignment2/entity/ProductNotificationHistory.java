package com.sparta.companyassignment2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "productNotificationHistory")
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


}
