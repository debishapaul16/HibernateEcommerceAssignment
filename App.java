package com.assignment.ecommerce;
import java.math.*;import java.util.*;
public class App {
 public static void main(String[] args){EcommerceService svc=new EcommerceService();try{
 Long cat=svc.saveCategory(new Category("Books","Books and study materials"));
 Long a=svc.saveProduct(new Product("Java Basics",new BigDecimal("499.00"),10),cat);
 Long b=svc.saveProduct(new Product("Hibernate Guide",new BigDecimal("699.00"),8),cat);
 Long user=svc.saveUser(new User("demo_customer","ChangeMe123!","customer@example.com",User.Role.CUSTOMER));
 Map<Long,Integer> cart=new LinkedHashMap<>();cart.put(a,2);cart.put(b,1);Long oid=svc.createOrder(user,cart);
 Orders order=svc.fetchOrder(oid);System.out.println(order+" by "+order.getUser().getUsername());
 order.getOrderDetails().forEach(d->System.out.println(d.getProduct().getName()+" x "+d.getQuantity()+" @ "+d.getUnitPrice()));
 System.out.println("Products by category: "+svc.byCategory(cat));System.out.println("Page 1: "+svc.pageProducts(0,5));
 }finally{HibernateUtil.shutdown();}}
}