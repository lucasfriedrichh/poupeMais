package br.upf.poupeMais.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "street", length = 60)
    private String street;

    @Column(name = "state", length = 75)
    private String state;

    @Column(name = "postalcode", length = 11)
    private String postalCode;

    @Column(name = "number", length = 5)
    private Integer number;


    @Embedded
    private Audit audit = new Audit();

    @PrePersist
    private void prePersist() {
        audit.setDateRegister(Instant.now());
    }

    @PreUpdate
    private void preUpdate() {
        audit.setDateLastEdition(Instant.now());
    }
}
