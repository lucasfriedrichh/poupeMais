package br.upf.poupeMais.api.controller;

import br.upf.poupeMais.domain.exception.EntityInUseApiException;
import br.upf.poupeMais.domain.exception.EntityNotFoundApiException;
import br.upf.poupeMais.domain.model.Perfil;
import br.upf.poupeMais.domain.model.User;
import br.upf.poupeMais.domain.repository.PerfilRepository;
import br.upf.poupeMais.domain.repository.UserRepository;
import br.upf.poupeMais.domain.services.PerfilService;
import br.upf.poupeMais.domain.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Perfil> findAll() {
        return repository.findAll();
    }

    @Autowired
    private PerfilService perfilService;

    @GetMapping("/{perfilId}")
    public ResponseEntity<?> findById(@PathVariable Integer perfilId) {
        Optional<Perfil> perfilOptional = repository.findById(perfilId);

        if (perfilOptional.isPresent()) {
            return ResponseEntity.ok(perfilOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Perfil add(@RequestBody Perfil perfil) {
        return repository.save(perfil);
    }


    @PutMapping("/{perfilId}")
    public ResponseEntity<?> update(
            @PathVariable Integer perfilId,
            @RequestBody Perfil perfil) {
        Optional<Perfil> perfilOptional = repository.findById(perfilId);

        if (perfilOptional.isPresent()){
            Perfil perfilCurrent = perfilOptional.get();

            BeanUtils.copyProperties(perfil, perfilCurrent, "id", "audit");

            return ResponseEntity.ok(repository.save(perfilCurrent));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{perfilId}")
    public ResponseEntity<?> delete(@PathVariable Integer perfilId) {
        try {
            perfilService.deleteById(perfilId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundApiException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntityInUseApiException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
