# Database Relations


> JPA annotations



- parent
- child


- unidirectional
- bidirectional


- Lazy
    - ...ToMany
- Eager
    - ...ToOne


- cascade
    - CascadeType.PERSIST
    - CascadeType.MERGE
    - CascadeType.REFRESH
    - CascadeType.REMOVE
    - CascadeType.DETACH
    - CascadeType.ALL
- JPA has no default
- Parent -> Child : make sense
- Child -> Parent : not useful and not recommended



Entities:

1. User
2. Address
3. Class
4. Course


Relations:

| # | Entities       | Relation     |
|---|----------------|--------------|
| 1 | User - Address | One-To-One   |
| 2 | User - Class   | One-To-Many  |
| 3 | Class - User   | Many-To-One  |
| 4 | User - Course  | Many-To-Many |


<hr />


### One-To-One


```java
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private long userId;

  private String username;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = AddressEntity.class)
  @JoinColumn(name = "addresses", referencedColumnName = "address_id", nullable = true)
  private AddressEntity address;
}
```

```java
import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long addressId;

    private String city;
}
```

- @JoinColumn used as a foreign key
- _name_ defines the name of the foreign key _table_
- _referencedColumnName_ indicates the _field name_ inside the _target column_


<hr />

