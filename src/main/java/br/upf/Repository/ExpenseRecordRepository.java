package br.upf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.upf.model.ExpenseRecord;

@Repository
public interface ExpenseRecordRepository extends JpaRepository<ExpenseRecord, Integer> {
}
