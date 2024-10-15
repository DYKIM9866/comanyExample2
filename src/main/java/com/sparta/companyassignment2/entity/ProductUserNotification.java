package com.sparta.companyassignment2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productUserNotification")
@Getter
@NoArgsConstructor
public class ProductUserNotification extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productId;

    @Column
    private Long userId;

    @Column(nullable = false)
    private boolean activeStatus;

}
