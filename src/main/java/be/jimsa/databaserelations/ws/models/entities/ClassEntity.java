package be.jimsa.databaserelations.ws.models.entities;

import jakarta.persistence.*;

import java.util.Set;

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
