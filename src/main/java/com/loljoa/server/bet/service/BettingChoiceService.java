package com.loljoa.server.bet.service;

import com.loljoa.server.bet.domain.BettingChoice;
import com.loljoa.server.bet.repository.BettingChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BettingChoiceService {

    private final BettingChoiceRepository bettingChoiceRepository;

    @Transactional
    public List<BettingChoice> findBettingChoices(){
        return bettingChoiceRepository.findAll();
    }


}
