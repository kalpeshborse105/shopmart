package com.shope.shopmart.Entities;
import java.time.Instant;
import java.util.List;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)


public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(min = 1, message = "Product name should be 1 or more characters")
    @Column(nullable = false, unique = true)
    private String productName;

    @NotNull
    @PastOrPresent(message = "Manufacturing date should be today's date or a past date")
    private Instant manufacturingDate;

    @NotNull
    @Size(min = 1, message = "Manufacturer name should be 1 or more characters")
    private String manufacturer;

    @NotNull
    @Min(value = 1, message = "Price value should be more than 0")
    private Double price;

    @NotNull
    @Size(min = 5, message = "Description should be 5 or more characters")
    private String description;

    private String imageUrl;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant modifiedAt;
    
    // #ManyToOne relationship with category

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
    name = "product_order", 
    joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private List<Orders> orders;
}