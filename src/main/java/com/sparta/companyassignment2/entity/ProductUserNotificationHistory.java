package com.sparta.companyassignment2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "productUserNotificationHistory")
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
    private String transferStatus;
}
