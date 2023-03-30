package br.upf.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upf.model.Address;
import br.upf.model.User;

@Repository
public interface AdressRepository extends JpaRepository<Address, Integer> {
}
