package com.liam.progdleapi.users;

import com.liam.progdleapi.users.entity.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface UsersRepository extends ListCrudRepository<User, UUID> {
    User findByUsername(String username);
}
