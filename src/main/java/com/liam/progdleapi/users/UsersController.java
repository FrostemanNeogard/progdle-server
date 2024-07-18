package com.liam.progdleapi.users;

import com.liam.progdleapi.users.entity.CreateUserDto;
import com.liam.progdleapi.users.entity.UpdateUserDto;
import com.liam.progdleapi.users.entity.User;
import com.liam.progdleapi.users.entity.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = usersService.getAllUsers();
        return ResponseEntity.ok(users.stream().map(UserDto::from).toList());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        User user = usersService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UserDto.from(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setProfilePictureSrc(dto.profilePictureSrc());
        User newUser = usersService.createUser(user);
        if (newUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity
                .created(URI.create("/api/users/" + newUser.getId()))
                .body(UserDto.from(newUser));
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDto> updateUserById(
            @PathVariable String username,
            @RequestBody UpdateUserDto dto) {
        User user = usersService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setUsername(dto.username());
        user.setProfilePictureSrc(dto.profilePictureSrc());
        User updatedUser = usersService.updateUser(user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
