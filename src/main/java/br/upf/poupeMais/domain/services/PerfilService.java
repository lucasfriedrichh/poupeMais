package br.upf.poupeMais.domain.services;

import java.util.List;
import java.util.Optional;

import br.upf.poupeMais.domain.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upf.poupeMais.domain.model.Perfil;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> findAll() {
        return perfilRepository.findAll();
    }

    public Optional<Perfil> findById(Integer id) {
        return perfilRepository.findById(id);
    }

    public Perfil save(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public void deleteById(Integer id) {
        perfilRepository.deleteById(id);
    }
}
    
