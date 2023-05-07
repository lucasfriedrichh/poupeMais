package br.upf.poupeMais.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upf.poupeMais.domain.model.User;
import br.upf.poupeMais.domain.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }


    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
