package br.upf.poupeMais.api.controller;

import br.upf.poupeMais.domain.model.Address;
import br.upf.poupeMais.domain.repository.AddressRepository;
import br.upf.poupeMais.domain.services.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Address> findAll() {
    return repository.findAll();
}

    @Autowired
    private AddressService addressService;


    @GetMapping("/{addressId}")
    public ResponseEntity<?> findById(@PathVariable Integer addressId) {
        Optional<Address> addressOptional = repository.findById(addressId);

        if (addressOptional.isPresent()) {
            return ResponseEntity.ok(addressOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address add(@RequestBody Address address) {
        return repository.save(address);
    }


    @PutMapping("/{adressId}")
    public ResponseEntity<?> update(
            @PathVariable Integer addressId,
            @RequestBody Address address) {
        Optional<Address> addressOptional = repository.findById(addressId);

        if (addressOptional.isPresent()){
            Address addressCurrent = addressOptional.get();

            BeanUtils.copyProperties(address, addressCurrent, "id", "audit");

            return ResponseEntity.ok(repository.save(addressCurrent));
        }

        return ResponseEntity.notFound().build();
    }



}
