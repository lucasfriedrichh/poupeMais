package br.upf.poupeMais.api.controller;

import br.upf.poupeMais.domain.exception.EntityInUseApiException;
import br.upf.poupeMais.domain.exception.EntityNotFoundApiException;
import br.upf.poupeMais.domain.model.ExpenseCategory;
import br.upf.poupeMais.domain.repository.ExpenseCategoryRepository;
import br.upf.poupeMais.domain.services.ExpenseCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class ExpenseCategoryController {
    @Autowired
    private ExpenseCategoryRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExpenseCategory> findAll() {
        return repository.findAll();
    }

    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @GetMapping("/{expenseCategoryId}")
    public ResponseEntity<?> findById(@PathVariable Integer expenseCategoryId) {
        Optional<ExpenseCategory> expenseCategoryOptional = repository.findById(expenseCategoryId);

        if (expenseCategoryOptional.isPresent()) {
            return ResponseEntity.ok(expenseCategoryOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseCategory add(@RequestBody ExpenseCategory expenseCategory) {
        return repository.save(expenseCategory);
    }


    @PutMapping("/{expenseCategoryId}")
    public ResponseEntity<?> update(
            @PathVariable Integer expenseCategoryId,
            @RequestBody ExpenseCategory expenseCategory ){
        Optional<ExpenseCategory> expenseCategoryOptional = repository.findById(expenseCategoryId);

        if (expenseCategoryOptional.isPresent()){
            ExpenseCategory expenseCategoryCurrent = expenseCategoryOptional.get();

            BeanUtils.copyProperties(expenseCategory, expenseCategoryCurrent, "id", "audit");

            return ResponseEntity.ok(repository.save(expenseCategoryCurrent));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{expenseCategoryId}")
    public ResponseEntity<?> delete(@PathVariable Integer expenseCategoryId) {
        try {
            expenseCategoryService.deleteById(expenseCategoryId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundApiException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntityInUseApiException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
