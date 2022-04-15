package com.loljoa.server.bet.service;

import com.loljoa.server.bet.domain.BettingChoice;
import com.loljoa.server.bet.domain.BettingState;
import com.loljoa.server.bet.domain.BettingGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

// MSA 이기에 Test 는 Mock 으로 객체를 생성하여, Class 단위면서도 classic 하게 단위테스트를 병행하자.
@ExtendWith(MockitoExtension.class)
public class BettingChoiceTest {

    @InjectMocks
    private BettingChoice bettingChoice;

    @Mock
    private BettingGame bettingGame;

    @Test
    @DisplayName("배팅테스트_객체 생성")
    public void 배팅리스트_검색_하나(){
        MockitoAnnotations.openMocks(this.bettingGame);
        ArrayList<BettingState> bettingState = new ArrayList<>();
        //bettingState.add(new BettingState())


        //choice.getBettingStates();

    }
}
