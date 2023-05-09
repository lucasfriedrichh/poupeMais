package br.upf.poupeMais.api.controller;

import br.upf.poupeMais.domain.exception.EntityInUseApiException;
import br.upf.poupeMais.domain.exception.EntityNotFoundApiException;
import br.upf.poupeMais.domain.model.Goals;
import br.upf.poupeMais.domain.model.Perfil;
import br.upf.poupeMais.domain.repository.GoalsRepository;
import br.upf.poupeMais.domain.services.GoalsService;
import br.upf.poupeMais.domain.services.PerfilService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/goals")
public class GoalsController {

    @Autowired
    private GoalsRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Goals> findAll() {
        return repository.findAll();
    }

    @Autowired
    private GoalsService goalsService;

    @GetMapping("/{goalsId}")
    public ResponseEntity<?> findById(@PathVariable Integer goalsId) {
        Optional<Goals> goalsOptional = repository.findById(goalsId);

        if (goalsOptional.isPresent()) {
            return ResponseEntity.ok(goalsOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Goals add(@RequestBody Goals goals) {
        return repository.save(goals);
    }


    @PutMapping("/{goalsId}")
    public ResponseEntity<?> update(
            @PathVariable Integer goalsId,
            @RequestBody Goals goals) {
        Optional<Goals> goalsOptional = repository.findById(goalsId);

        if (goalsOptional.isPresent()){
            Goals goalsCurrent = goalsOptional.get();

            BeanUtils.copyProperties(goals, goalsCurrent, "id", "audit");

            return ResponseEntity.ok(repository.save(goalsCurrent));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{goalsId}")
    public ResponseEntity<?> delete(@PathVariable Integer goalsId) {
        try {
            goalsService.deleteById(goalsId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundApiException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntityInUseApiException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


}
