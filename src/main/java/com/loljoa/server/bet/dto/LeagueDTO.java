package com.loljoa.server.bet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class LeagueDTO {
    private Long id;
    private String leagueName;
    private String weekNum;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long teamId;
}
