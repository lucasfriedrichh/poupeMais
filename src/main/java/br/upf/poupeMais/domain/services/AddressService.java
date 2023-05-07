package br.upf.poupeMais.domain.services;

import java.util.List;
import java.util.Optional;

import br.upf.poupeMais.domain.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upf.poupeMais.domain.model.Address;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Optional<Address> findById(Integer id) {
        return addressRepository.findById(id);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public void deleteById(Integer id) {
        addressRepository.deleteById(id);
    }
}

