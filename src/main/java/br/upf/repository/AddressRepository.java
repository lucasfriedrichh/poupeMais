package br.upf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upf.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
