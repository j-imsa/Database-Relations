package be.jimsa.databaserelations.ws.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long id;

    private String name;
}