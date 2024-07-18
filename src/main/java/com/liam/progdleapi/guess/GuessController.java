package com.liam.progdleapi.guess;

import com.liam.progdleapi.guess.entity.Guess;
import com.liam.progdleapi.guess.entity.GuessDto;
import com.liam.progdleapi.languages.LanguagesService;
import com.liam.progdleapi.languages.entity.Language;
import com.liam.progdleapi.users.UsersService;
import com.liam.progdleapi.users.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guess")
@CrossOrigin
public class GuessController {
    private final GuessService guessService;
    private final LanguagesService languagesService;
    private final UsersService usersService;

    public GuessController(
            GuessService guessService,
            LanguagesService languagesService,
            UsersService usersService) {
        this.guessService = guessService;
        this.languagesService = languagesService;
        this.usersService = usersService;
    }

    @GetMapping("/{language}")
    public ResponseEntity<GuessDto> guess(
            @RequestHeader("Authorization") String token,
            @PathVariable String language) {
        Language guessedLanguage = languagesService.findLanguageByName(language);
        if (guessedLanguage == null) {
            return ResponseEntity.notFound().build();
        }

        Guess guess = this.guessService.validateGuess(guessedLanguage);

        GuessDto dto = GuessDto.from(guess);
        if (token != null && !token.isEmpty()) {
            long score = this.guessService.getScoreFromGuess(guess);
            User user = usersService.getUserByToken(token);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto);
            }
            user.setScore(user.getScore() + score);
            usersService.updateUser(user);
        }

        return ResponseEntity.ok(dto);
    }
}
