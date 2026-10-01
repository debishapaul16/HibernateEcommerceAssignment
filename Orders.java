package com.assignment.ecommerce;
import jakarta.persistence.*; import java.math.*; import java.time.*; import java.util.*;
@Entity @Table(name="orders")
public class Orders {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private LocalDateTime orderDate;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal totalAmount=BigDecimal.ZERO;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="user_id",nullable=false) private User user;
 @OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY) private List<OrderDetails> orderDetails=new ArrayList<>();
 protected Orders(){} public Orders(User u){user=u;orderDate=LocalDateTime.now();}
 public void addDetail(OrderDetails d){orderDetails.add(d);d.setOrder(this);totalAmount=totalAmount.add(d.getUnitPrice().multiply(BigDecimal.valueOf(d.getQuantity())));}
 public Long getId(){return id;} public User getUser(){return user;} public List<OrderDetails> getOrderDetails(){return orderDetails;}
 public String toString(){return "Order{id="+id+", total="+totalAmount+"}";}
}