package com.loljoa.server.bet.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class BettingState {
    @Id
    @GeneratedValue
    private Long stateId;

    @Column(nullable = false)
    private Long point;

    @Column
    private LocalDateTime betTime;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account better;

    @ManyToOne(targetEntity = BettingChoice.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "choice_id")
    private BettingChoice choice ;

    public static BettingState createBettingState(Account account,BettingChoice bettingChoice,Long point){
        BettingState bettingState = new BettingState();
        bettingState.setBetter(account);
        bettingState.setChoice(bettingChoice);
        bettingState.setPoint(point);
        bettingState.setBetTime(LocalDateTime.now());
        return bettingState;
    }

}
