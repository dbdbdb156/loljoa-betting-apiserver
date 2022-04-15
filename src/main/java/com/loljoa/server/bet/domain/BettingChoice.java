package com.loljoa.server.bet.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BettingChoice {
    @Id
    @GeneratedValue
    private Long choiceId;

    @Column
    private String name;

    @ManyToOne(targetEntity = BettingGame.class)
    private BettingGame targetGame;

    @OneToMany(mappedBy = "choice",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BettingState> bettingstates = new ArrayList<>();

    public static BettingChoice createBettingChoice(BettingGame bettingGame){
        BettingChoice bettingchoice = new BettingChoice();
        bettingchoice.setTargetGame(bettingGame);
        return bettingchoice;
    }
    public BettingChoice(String name, BettingGame targetGame) {
        this.name = name;
        this.targetGame = targetGame;
    }

}
