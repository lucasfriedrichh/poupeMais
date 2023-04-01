package br.upf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upf.model.ExpenseCategory;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Integer> {
}
