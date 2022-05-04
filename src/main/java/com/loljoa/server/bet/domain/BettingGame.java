package com.loljoa.server.bet.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class BettingGame {
    @Id
    @GeneratedValue
    private Long gameId;

    private String title;

    private Long leagueId;

    public BettingGame(String title, Long leagueId) {
        this.title = title;
        this.leagueId = leagueId;
    }

    @OneToMany(mappedBy = "targetGame")
    private List<BettingChoice> choices = new ArrayList<>();

}
