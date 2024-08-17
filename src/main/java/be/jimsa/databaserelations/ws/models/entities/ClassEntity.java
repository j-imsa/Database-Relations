package be.jimsa.databaserelations.ws.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "classes")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private long id;

    private String name;
}
