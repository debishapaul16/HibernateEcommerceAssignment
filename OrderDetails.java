package com.assignment.ecommerce;
import jakarta.persistence.*; import java.math.*;
@Entity @Table(name="order_details")
public class OrderDetails {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private Integer quantity;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal unitPrice;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="order_id",nullable=false) private Orders order;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="product_id",nullable=false) private Product product;
 protected OrderDetails(){} public OrderDetails(Product p,int q){if(q<=0)throw new IllegalArgumentException("Quantity must be positive");product=p;quantity=q;unitPrice=p.getPrice();}
 void setOrder(Orders o){order=o;} public int getQuantity(){return quantity;} public BigDecimal getUnitPrice(){return unitPrice;} public Product getProduct(){return product;}
}