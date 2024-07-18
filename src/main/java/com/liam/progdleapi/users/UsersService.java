package com.liam.progdleapi.users;

import com.liam.progdleapi.users.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public User getUserById(UUID id) {
        return usersRepository.findById(id).orElse(null);
    }

    public User updateUser(User user) {
        User userToUpdate = usersRepository.findById(user.getId()).orElse(null);
        if (userToUpdate == null) {
            return null;
        }
        return usersRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public User createUser(User user) {
        if (getUserByUsername(user.getUsername()) != null) {
            return null;
        }
        return usersRepository.save(user);
    }
}
