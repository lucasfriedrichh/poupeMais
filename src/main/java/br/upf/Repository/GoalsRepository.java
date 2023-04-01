package br.upf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upf.model.Goals;

@Repository
public interface GoalsRepository extends JpaRepository<Goals, Integer> {
}
