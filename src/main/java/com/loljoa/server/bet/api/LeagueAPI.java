package com.loljoa.server.bet.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loljoa.server.bet.config.GsonLocalDateTimeAdapter;
import com.loljoa.server.bet.domain.League;
import com.loljoa.server.bet.dto.LeagueDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.loljoa.server.bet.domain.Contents.SCHEDULE_URL;

/*
## LEAGUE
        1. 선택한 리그 1개 받아오기
        - column : gameid ,
        2. 배팅 시간이 지났는지 확인 함수 : 금액 환급 위함
        - boolean : true or false

*/
@Service
public class LeagueAPI {

    private static RestTemplate restTemplate;
    public List<LeagueDTO> leagueScheduleAll(String query){

        String fullURL = SCHEDULE_URL+"api/league/schedule/all" + query;
        // msa 간 통신
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(fullURL,String.class);

        // json 변환 list
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
        // parsing
        String getBody = result.getBody();
        getBody = getBody.substring(getBody.indexOf("["),getBody.lastIndexOf("]") + 1);

        return gson.fromJson(getBody, new TypeToken<ArrayList<LeagueDTO>>(){}.getType());
    }
    public LeagueDTO leagueSchedule(Long leagueId){
        String fullURL = SCHEDULE_URL+"api/league/schedule/leagueid/"+leagueId;

        // msa 간 통신
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(fullURL,String.class);

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
        return gson.fromJson(result.getBody(),LeagueDTO.class);

    }

    public String getMessage(boolean success){
        Map<String,String> map = new HashMap<>();
        String message = success ? "true" : "false";
        map.put("message",message);
        return new Gson().toJson(map);
    }

}
