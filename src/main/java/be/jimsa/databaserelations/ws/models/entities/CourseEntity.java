package be.jimsa.databaserelations.ws.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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