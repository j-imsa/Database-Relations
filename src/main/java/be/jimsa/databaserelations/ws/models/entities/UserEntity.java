package be.jimsa.databaserelations.ws.models.entities;

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
