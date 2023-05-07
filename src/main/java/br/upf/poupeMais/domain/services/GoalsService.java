package br.upf.poupeMais.domain.services;

import java.util.List;
import java.util.Optional;

import br.upf.poupeMais.domain.repository.GoalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upf.poupeMais.domain.model.Goals;

@Service
public class GoalsService {

    @Autowired
    private GoalsRepository goalsRepository;

    public List<Goals> findAll() {
        return goalsRepository.findAll();
    }

    public Optional<Goals> findById(Integer id) {
        return goalsRepository.findById(id);
    }

    public Goals save(Goals goal) {
        return goalsRepository.save(goal);
    }

    public void deleteById(Integer id) {
        goalsRepository.deleteById(id);
    }
}
