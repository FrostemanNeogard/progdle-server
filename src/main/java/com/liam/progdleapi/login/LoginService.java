package com.liam.progdleapi.login;

import com.liam.progdleapi.users.UsersService;
import com.liam.progdleapi.users.entity.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UsersService usersService;

    public LoginService(UsersService usersService) {
        this.usersService = usersService;
    }

    public String login(String username, String password) {
        User user = usersService.getUserByUsername(username);
        if (user == null) {
            return null;
        }

        if (!user.getPassword().equals(password)) {
            return null;
        }

        return "VALID_LOGIN_TOKEN";
    }

}
