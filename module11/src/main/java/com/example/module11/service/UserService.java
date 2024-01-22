package com.example.module11.service;

import com.example.module11.entity.User;
import com.example.module11.exception.ItemNotFoundException;
import com.example.module11.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException("User not found, id = " + id));
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User update(User user) {
        findById(user.getId());
        return repository.save(user);
    }

    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
