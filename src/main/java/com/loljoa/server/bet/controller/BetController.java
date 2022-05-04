package com.loljoa.server.bet.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loljoa.server.bet.api.AccountAPI;
import com.loljoa.server.bet.api.GameDataAPI;
import com.loljoa.server.bet.api.LeagueAPI;
import com.loljoa.server.bet.config.GsonLocalDateTimeAdapter;
import com.loljoa.server.bet.domain.Account;
import com.loljoa.server.bet.domain.BettingGame;
import com.loljoa.server.bet.domain.BettingState;
import com.loljoa.server.bet.domain.League;
import com.loljoa.server.bet.dto.BettingStateDTO;
import com.loljoa.server.bet.dto.LeagueDTO;
import com.loljoa.server.bet.repository.AccountRepository;
import com.loljoa.server.bet.service.AccountService;
import com.loljoa.server.bet.service.BettingStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BetController {

    private final GameDataAPI gameDataAPI;
    private final AccountAPI accountAPI;
    private final LeagueAPI leagueAPI ;
    private final BettingStateService bettingStateService;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    // 배팅 리스트
    @GetMapping("/bet/all")
    public ResponseEntity<String> betGame(){
        // Need item - 1.Account 2. Legue -> make BettingChoice + BettingState

        List<BettingState> choice = bettingStateService.findBettingStates();
        List<BettingStateDTO> list = new ArrayList();

        for(int i=0;i<choice.size();++i)
            list.add(new BettingStateDTO(choice.get(i).getStateId(),choice.get(i).getPoint(),choice.get(i).getBetTime()));

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
        return new ResponseEntity<String>(gson.toJson(list), HttpStatus.OK);

    }

    // 배팅 하기
    // 파라미터 : userid , team, point
    // /api/betting?username=1&gamedate=3&point=100 <- http 통신  / RESTFUL /bet/username/1/gamedata/2/point/100
    // header , href = 주소
    @PostMapping("/bet/username/{username}/gamedata/{gametitle}/point/{point}")
    public ResponseEntity<String> bettingList(HttpServletRequest req, HttpServletResponse res
    ,@PathVariable(value="username") String username
    ,@PathVariable(value="gametitle") String gametitle
    ,@PathVariable(value="point") Long point)throws ServletException, IOException {

        //Account account = new Account(1L,"TestMan","admin",1000L);

        // Account 파라미터로 받아야함. Next Change Account.findOne
        Account account = accountService.findAccountByName(username);
        // GameData 파라미터로 받아야함.
        BettingGame bettingGame = gameDataAPI.gamedata(gametitle);
        // league 받기
        LeagueDTO leagueDTO = leagueAPI.leagueSchedule(bettingGame.getLeagueId());

        boolean enoughPoint = false;
        boolean applyChangePoint = false;

        if(bettingStateService.timeIsOver(leagueDTO.getStartTime())){
            enoughPoint = true;
            // 경기 시작전 배팅 가능하면
            if(bettingStateService.pointIsEnough(account.getPoint(),point)){
                applyChangePoint = accountService.removePoint(account,point);
                if(applyChangePoint) bettingStateService.makeBettingState(account,bettingGame,point);
                else{
                    // remove 롤백 = pointAdd.
                }
            }
        }

        // message = "true" or "false"
        String message = leagueAPI.getMessage(enoughPoint&&applyChangePoint);
        return ResponseEntity.ok(message);
    }

}
