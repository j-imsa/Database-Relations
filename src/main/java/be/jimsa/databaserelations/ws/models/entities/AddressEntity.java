package be.jimsa.databaserelations.ws.models.entities;

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
