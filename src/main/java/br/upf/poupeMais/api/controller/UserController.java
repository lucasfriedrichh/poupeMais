package br.upf.poupeMais.api.controller;

import java.util.List;
import java.util.Optional;

import br.upf.poupeMais.domain.exception.EntityInUseApiException;
import br.upf.poupeMais.domain.exception.EntityNotFoundApiException;
import br.upf.poupeMais.domain.repository.UserRepository;
import br.upf.poupeMais.domain.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.upf.poupeMais.domain.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> findById(@PathVariable Integer userId) {
        Optional<User> userOptional = repository.findById(userId);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User add(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(
            @PathVariable Integer userId,
            @RequestBody User user) {
        Optional<User> userOptional = repository.findById(userId);

        if (userOptional.isPresent()){
            User userCurrent = userOptional.get();

            BeanUtils.copyProperties(user, userCurrent, "id", "audit");

            return ResponseEntity.ok(repository.save(userCurrent));
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable Integer userId) {
        try {
            userService.delete(userId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundApiException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntityInUseApiException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


}
