package br.upf.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upf.model.Goals;
import br.upf.model.User;

@Repository
public interface GoalsRepository extends JpaRepository<Goals, Integer> {
}
