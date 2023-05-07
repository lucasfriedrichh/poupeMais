package br.upf.poupeMais.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upf.poupeMais.domain.model.Address;

@Repository
public interface AddressRepository
        extends JpaRepository<Address, Integer> {
}
