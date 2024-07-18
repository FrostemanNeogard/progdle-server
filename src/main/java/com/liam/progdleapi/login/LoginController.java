package com.liam.progdleapi.login;

import com.liam.progdleapi.login.entity.LoginDto;
import com.liam.progdleapi.users.UsersService;
import com.liam.progdleapi.users.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;
    private final UsersService usersService;

    public LoginController(LoginService loginService, UsersService usersService) {
        this.loginService = loginService;
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<String> loginByUsernameAndPassword(@RequestBody LoginDto dto) {
        User user = usersService.getUserByUsername(dto.username());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        String token = loginService.login(dto.username(), dto.password());
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(token);
    }
}
