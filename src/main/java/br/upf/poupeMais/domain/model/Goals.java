package br.upf.poupeMais.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value", length = 15)
    private Float value;

    @Column(name = "description", length = 60)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "expense_category_id")
    private ExpenseCategory expenseCategory;

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
