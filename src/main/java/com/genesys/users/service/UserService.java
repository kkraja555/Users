package com.genesys.users.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.genesys.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> listAll() {
        return repo.findAll();
    }

    public User save(User User) {
       return repo.save(User);
    }

    public Optional<User> getUserById(Integer id) {
        return repo.findById(id);
    }
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}