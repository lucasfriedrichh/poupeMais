package br.upf.poupeMais.domain.services;

import java.util.List;
import java.util.Optional;

import br.upf.poupeMais.domain.repository.ExpenseRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upf.poupeMais.domain.model.ExpenseRecord;

@Service
public class ExpenseRecordService {

    @Autowired
    private ExpenseRecordRepository expenseRecordRepository;

    public List<ExpenseRecord> findAll() {
        return expenseRecordRepository.findAll();
    }

    public Optional<ExpenseRecord> findById(Integer id) {
        return expenseRecordRepository.findById(id);
    }

    public ExpenseRecord save(ExpenseRecord expenseRecord) {
        return expenseRecordRepository.save(expenseRecord);
    }

    public void deleteById(Integer id) {
        expenseRecordRepository.deleteById(id);
    }
}
