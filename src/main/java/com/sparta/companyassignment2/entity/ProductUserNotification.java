package com.sparta.companyassignment2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "productUserNotification")
public class ProductUserNotification extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}
