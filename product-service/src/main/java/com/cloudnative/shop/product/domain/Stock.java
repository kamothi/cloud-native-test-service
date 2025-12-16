package com.cloudnative.shop.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    private Long productId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Version
    private Long version;

    public Stock(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void decrease(int amount) {
        if (quantity < amount) {
            throw new IllegalStateException("재고 부족");
        }
        this.quantity -= amount;
    }

    public void increase(int amount) {
        this.quantity += amount;
    }
}
