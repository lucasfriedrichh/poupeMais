package br.upf.poupeMais.api.controller;

import br.upf.poupeMais.domain.exception.EntityInUseApiException;
import br.upf.poupeMais.domain.exception.EntityNotFoundApiException;
import br.upf.poupeMais.domain.model.ExpenseRecord;
import br.upf.poupeMais.domain.repository.ExpenseRecordRepository;
import br.upf.poupeMais.domain.services.ExpenseCategoryService;
import br.upf.poupeMais.domain.services.ExpenseRecordService;
import br.upf.poupeMais.domain.services.PerfilService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expenserecord")
public class ExpenseRecordController {

    @Autowired
    private ExpenseRecordRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExpenseRecord> findAll() {
        return repository.findAll();
    }

    @Autowired
    private ExpenseRecordService expenseRecordService;

    @GetMapping("/{expenseRecordId}")
    public ResponseEntity<?> findById(@PathVariable Integer expenseRecordId) {
        Optional<ExpenseRecord> expenseRecordOptional = repository.findById(expenseRecordId);

        if (expenseRecordOptional.isPresent()) {
            return ResponseEntity.ok(expenseRecordOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseRecord add(@RequestBody ExpenseRecord expenseRecord) {
        return repository.save(expenseRecord);
    }


    @PutMapping("/{expenseRecordId}")
    public ResponseEntity<?> update(
            @PathVariable Integer expenseRecordId,
            @RequestBody ExpenseRecord expenseRecord ){
        Optional<ExpenseRecord> expenseRecordOptional = repository.findById(expenseRecordId);

        if (expenseRecordOptional.isPresent()){
            ExpenseRecord expenseRecordCurrent = expenseRecordOptional.get();

            BeanUtils.copyProperties(expenseRecord, expenseRecordCurrent, "id", "audit");

            return ResponseEntity.ok(repository.save(expenseRecordCurrent));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{expenseRecordId}")
    public ResponseEntity<?> delete(@PathVariable Integer expenseRecordId) {
        try {
            expenseRecordService.deleteById(expenseRecordId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundApiException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntityInUseApiException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


}
