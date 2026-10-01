package com.assignment.ecommerce;
import jakarta.persistence.*; import java.math.*;
@Entity @Table(name="products")
@NamedQuery(name="Product.findByCategory",query="from Product p where p.category.id=:categoryId and p.deleted=false")
public class Product {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=150) private String name;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal price;
 @Column(nullable=false) private Integer stockQuantity;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="category_id",nullable=false) private Category category;
 @Column(nullable=false) private boolean deleted=false;
 protected Product(){} public Product(String n,BigDecimal p,int stock){name=n;price=p;stockQuantity=stock;}
 void setCategory(Category c){category=c;} public void decreaseStock(int q){if(q<=0||q>stockQuantity)throw new IllegalArgumentException("Invalid/insufficient stock");stockQuantity-=q;}
 public void markDeleted(){deleted=true;} public Long getId(){return id;} public String getName(){return name;} public BigDecimal getPrice(){return price;} public int getStockQuantity(){return stockQuantity;} public boolean isDeleted(){return deleted;}
 public String toString(){return "Product{"+id+", "+name+", "+price+", stock="+stockQuantity+"}";}
}