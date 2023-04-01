package br.upf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upf.model.Address;
import br.upf.repository.AddressRepository;

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

