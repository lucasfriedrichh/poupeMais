package br.upf.poupeMais.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false, name = "name")
    private String name;


    @Column(nullable = false, unique = true, name = "email")
    private String email;


    @Column(nullable = false, name = "password")
    private String password;


    @Column(nullable = false, unique = true, name = "cpf")
    private String cpf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

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
