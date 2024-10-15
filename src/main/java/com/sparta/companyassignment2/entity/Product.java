package com.sparta.companyassignment2.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
public class Product extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restockRound", nullable = false)
    private Integer restockRound;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductNotificationHistory> restockNotificationHistory;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductUserNotification> restockNotificationUser;

    public void update(int restockRound, int restockCnt){
        this.restockRound = restockRound;
        this.stock += restockCnt;
    }
}
