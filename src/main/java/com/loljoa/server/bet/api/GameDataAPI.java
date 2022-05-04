package com.loljoa.server.bet.api;

import com.google.gson.Gson;
import com.loljoa.server.bet.domain.BettingGame;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import static com.loljoa.server.bet.domain.Contents.SCHEDULE_URL;

/*
## GAME_DATA
        1. 선택 게임 1개 받아오기
        - column : gameid , targetGame ;

*/
@Service
public class GameDataAPI {
    @GetMapping
    public BettingGame gamedata(String title){

        // msa 간 통신
        String fullURL = SCHEDULE_URL + "api/gamedata/title/" + title ;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> result = restTemplate.getForEntity(fullURL,String.class);
        return new Gson().fromJson(result.getBody(), BettingGame.class);
    }

}
