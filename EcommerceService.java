package com.assignment.ecommerce;
import org.hibernate.*; import jakarta.persistence.criteria.*; import java.util.*;
public class EcommerceService {
 private <T>T tx(java.util.function.Function<Session,T> f){Transaction t=null;try(Session s=HibernateUtil.getSessionFactory().openSession()){t=s.beginTransaction();T v=f.apply(s);t.commit();return v;}catch(RuntimeException e){if(t!=null&&t.isActive())t.rollback();throw e;}}
 public Long saveCategory(Category c){return tx(s->{s.persist(c);return c.getId();});}
 public Long saveProduct(Product p,Long categoryId){return tx(s->{Category c=s.get(Category.class,categoryId);if(c==null)throw new IllegalArgumentException("Category missing");p.setCategory(c);s.persist(p);return p.getId();});}
 public Long saveUser(User u){return tx(s->{s.persist(u);return u.getId();});}
 public Long createOrder(Long userId,Map<Long,Integer> cart){return tx(s->{User u=s.get(User.class,userId);if(u==null)throw new IllegalArgumentException("User missing");Orders o=new Orders(u);for(var e:cart.entrySet()){Product p=s.get(Product.class,e.getKey());if(p==null||p.isDeleted())throw new IllegalArgumentException("Product unavailable");p.decreaseStock(e.getValue());o.addDetail(new OrderDetails(p,e.getValue()));}s.persist(o);return o.getId();});}
 public List<Product> byCategory(Long id){try(Session s=HibernateUtil.getSessionFactory().openSession()){return s.createNamedQuery("Product.findByCategory",Product.class).setParameter("categoryId",id).getResultList();}}
 public List<Product> pageProducts(int page,int size){try(Session s=HibernateUtil.getSessionFactory().openSession()){return s.createQuery("from Product p where p.deleted=false order by p.name",Product.class).setFirstResult(page*size).setMaxResults(size).getResultList();}}
 public List<Product> criteriaPageProducts(int page,int size){try(Session s=HibernateUtil.getSessionFactory().openSession()){
  CriteriaBuilder cb=s.getCriteriaBuilder();CriteriaQuery<Product> cq=cb.createQuery(Product.class);Root<Product> root=cq.from(Product.class);
  cq.select(root).where(cb.isFalse(root.get("deleted"))).orderBy(cb.asc(root.get("name")));
  return s.createQuery(cq).setFirstResult(page*size).setMaxResults(size).getResultList();
 }}
 public void softDelete(Long id){tx(s->{Product p=s.get(Product.class,id);if(p!=null)p.markDeleted();return null;});}
 public Orders fetchOrder(Long id){try(Session s=HibernateUtil.getSessionFactory().openSession()){return s.createQuery("select distinct o from Orders o join fetch o.user left join fetch o.orderDetails d left join fetch d.product where o.id=:id",Orders.class).setParameter("id",id).uniqueResult();}}
}