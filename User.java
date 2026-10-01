package com.assignment.ecommerce;
import jakarta.persistence.*; import java.util.*;
@Entity @Table(name="users")
public class User {
 public enum Role{ADMIN,CUSTOMER}
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=80) private String username;
 @Column(nullable=false,length=100) private String password;
 @Column(nullable=false,unique=true,length=150) private String email;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Role role;
 @OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY) private List<Orders> orders=new ArrayList<>();
 protected User(){} public User(String u,String raw,String e,Role r){username=u;password=org.mindrot.jbcrypt.BCrypt.hashpw(raw,org.mindrot.jbcrypt.BCrypt.gensalt());email=e;role=r;}
 public Long getId(){return id;} public String getUsername(){return username;} public List<Orders> getOrders(){return orders;}
}