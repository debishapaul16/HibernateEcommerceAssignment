# Hibernate ORM Implementation — E-commerce

Maven Java console app matching the assignment's five entities and mappings.

### Implemented
- Category, Product, User, Orders, OrderDetails with generated primary keys and constraints.
- Category–Product, User–Orders, Orders–OrderDetails and Product–OrderDetails relationships.
- Cascade on order details; lazy associations; transactional persistence.
- Create categories/products/users, place orders with multiple details, update stock, fetch an order with its user and products.
- H2 file-backed database; BCrypt password hashing.
- Bonus: named query by category, pagination, and soft-delete flag.

### Run
Requirements: JDK 17+ and Maven 3.8+.

```bash
mvn clean compile
mvn exec:java
```

Hibernate creates/updates the schema automatically. Database files are stored in `data/`.
The demo inserts sample data each run; remove the `data/` folder to reset it.

### Database configuration
Default H2 URL: `jdbc:h2:./data/ecommerce;DB_CLOSE_ON_EXIT=FALSE`, user `sa`, blank password.
For MySQL, add the MySQL Connector/J dependency and change the connection settings/dialect in `HibernateUtil.java`. Do not commit real credentials.

### Submission
Push the extracted project directory to a new GitHub repository. Do not upload `target/` or local database files.
