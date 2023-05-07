package br.upf.poupeMais.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.Instant;

@Embeddable
@Data
public class Audit {

    @Column(name = "date_register",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dateRegister;

    @Column(name = "date_last_edition",
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dateLastEdition;

}
