package be.jimsa.databaserelations.ws.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    private String username;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = AddressEntity.class)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = true)
    private AddressEntity address;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = true)
    private ClassEntity classEntity;

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
