package com.loljoa.server.bet.service;

import com.loljoa.server.bet.domain.*;
import com.loljoa.server.bet.repository.BettingChoiceRepository;
import com.loljoa.server.bet.repository.BettingStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BettingStateService {

    private final BettingStateRepository bettingStateRepository;
    private final BettingChoiceRepository bettingChoiceRepository;

    @Transactional
    public Long makeBettingState(Account account, BettingGame gamedata, Long point){

        // BettingChoice 생성
        BettingChoice bettingChoice = BettingChoice.createBettingChoice(gamedata);
        bettingChoiceRepository.save(bettingChoice);

        // BettingState 생성
        BettingState bettingState = BettingState.createBettingState(account,bettingChoice,point);
        bettingStateRepository.save(bettingState);

        return bettingState.getStateId();
    }
    public List<BettingState> findBettingStates(){
        return bettingStateRepository.findAll();
    }

    public boolean timeIsOver(LocalDateTime gameStartTime){

        if(gameStartTime.isBefore(LocalDateTime.now())) return true;
        return false;
    }
    public boolean pointIsEnough(Long userPoint,Long betPoint){
        return userPoint >= betPoint ? true : false;
    }

}
