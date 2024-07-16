package com.liam.progdleapi.guess.entity;

import com.liam.progdleapi.guess.GuessService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Guess {
    private GuessService.STATES memorySafe;
    private GuessService.STATES domain;
    private GuessService.STATES name;
    private GuessService.STATES os;
    private GuessService.STATES paradigm;
    private GuessService.STATES typing;
}
