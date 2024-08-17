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
- _name_ defines the name of the foreign key _column_
- _referencedColumnName_ indicates the _field name_ inside the _target column_

<hr />

### One-To-Many & Many-To-One

> Usually, the child entity is one that owns the relationship and the parent entity contains the @OneToMany annotation.

```java

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    private String username;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = true)
    private ClassEntity classEntity;
}
```

- child entity has JoinColumn
- child entity is the owner of this relationship

```java

@Entity
@Table(name = "classes")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private long id;

    private String name;

    @OneToMany(
            mappedBy = "classEntity",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            targetEntity = UserEntity.class
    )
    private Set<UserEntity> users;
}
```

- @OneToMany has mappedBy
- mappedBy means bidirectional relationship

### Many-To-Many

```java

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    private String username;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "users_courses",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
            }
    )
    private Set<CourseEntity> courses = new HashSet<>();
}
```

```java

@Entity
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long id;

    private String name;

    @ManyToMany(
            mappedBy = "courses",
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST
    )
    private Set<UserEntity> users = new HashSet<>();
}
```

> Usually, we mention _mappedBy_ parameter on the owning entity

