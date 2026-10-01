package com.assignment.ecommerce;
import org.hibernate.*; import org.hibernate.cfg.Configuration;
public final class HibernateUtil {
 private static final SessionFactory FACTORY=build(); private HibernateUtil(){}
 private static SessionFactory build(){Configuration c=new Configuration();
 c.setProperty("hibernate.connection.driver_class","org.h2.Driver");c.setProperty("hibernate.connection.url","jdbc:h2:./data/ecommerce;DB_CLOSE_ON_EXIT=FALSE");
 c.setProperty("hibernate.connection.username","sa");c.setProperty("hibernate.connection.password","");c.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
 c.setProperty("hibernate.hbm2ddl.auto","update");c.setProperty("hibernate.show_sql","false");
 c.addAnnotatedClass(Category.class).addAnnotatedClass(Product.class).addAnnotatedClass(User.class).addAnnotatedClass(Orders.class).addAnnotatedClass(OrderDetails.class);return c.buildSessionFactory();}
 public static SessionFactory getSessionFactory(){return FACTORY;} public static void shutdown(){FACTORY.close();}
}