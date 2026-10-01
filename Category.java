package com.assignment.ecommerce;
import jakarta.persistence.*; import java.util.*;
@Entity @Table(name="categories")
public class Category {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=100) private String name;
 @Column(length=500) private String description;
 @OneToMany(mappedBy="category",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY) private List<Product> products=new ArrayList<>();
 protected Category(){} public Category(String n,String d){name=n;description=d;}
 public Long getId(){return id;} public String getName(){return name;} public List<Product> getProducts(){return products;}
}