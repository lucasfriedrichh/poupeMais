package br.upf.poupeMais.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.upf.poupeMais.domain.model.ExpenseRecord;
import br.upf.poupeMais.domain.services.ExpenseRecordService;

@RestController
@RequestMapping("/expense-records")
public class ExpenseRecordController {

    @Autowired
    private ExpenseRecordService expenseRecordService;

    @GetMapping("/")
    public ResponseEntity<List<ExpenseRecord>> getAllExpenseRecords() {
        List<ExpenseRecord> expenseRecords = expenseRecordService.findAllExpenseRecords();
        return ResponseEntity.ok(expenseRecords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseRecord> getExpenseRecordById(@PathVariable("id") Integer id) {
        ExpenseRecord expenseRecord = expenseRecordService.findExpenseRecordById(id);
        if (expenseRecord == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(expenseRecord);
    }

    @PostMapping("/")
    public ResponseEntity<ExpenseRecord> createExpenseRecord(@RequestBody ExpenseRecord expenseRecord) {
        expenseRecord = expenseRecordService.saveExpenseRecord(expenseRecord);
        return ResponseEntity.ok(expenseRecord);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseRecord> updateExpenseRecord(@PathVariable("id") Integer id, @RequestBody ExpenseRecord expenseRecord) {
        expenseRecord.setId(id);
        expenseRecord = expenseRecordService.updateExpenseRecord(expenseRecord);
        if (expenseRecord == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(expenseRecord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpenseRecord(@PathVariable("id") Integer id) {
        expenseRecordService.deleteExpenseRecord(id);
        return ResponseEntity.ok().build();
    }
}
