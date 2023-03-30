package br.upf.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upf.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
}
