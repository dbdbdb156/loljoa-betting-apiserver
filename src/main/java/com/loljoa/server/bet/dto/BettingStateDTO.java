package com.loljoa.server.bet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class BettingStateDTO {
    private Long id;
    private Long point;
    private LocalDateTime betTime;
}
