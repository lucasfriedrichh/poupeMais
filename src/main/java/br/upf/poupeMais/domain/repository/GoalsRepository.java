package br.upf.poupeMais.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upf.poupeMais.domain.model.Goals;

@Repository
public interface GoalsRepository
        extends JpaRepository<Goals, Integer> {
}
