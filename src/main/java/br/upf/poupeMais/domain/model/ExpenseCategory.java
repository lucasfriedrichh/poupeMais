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
public class ExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "description", length = 60)
    private String description;

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

