package br.upf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upf.model.ExpenseCategory;
import br.upf.repository.ExpenseCategoryRepository;

@Service
public class ExpenseCategoryService {

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    public List<ExpenseCategory> findAll() {
        return expenseCategoryRepository.findAll();
    }

    public Optional<ExpenseCategory> findById(Integer id) {
        return expenseCategoryRepository.findById(id);
    }

    public ExpenseCategory save(ExpenseCategory expenseCategory) {
        return expenseCategoryRepository.save(expenseCategory);
    }

    public void deleteById(Integer id) {
        expenseCategoryRepository.deleteById(id);
    }
}
