package com.loljoa.server.bet.api;

/*
# 필요
- JSON 파싱

# 기능
## Account
1. 유저의 포인트 받아오기
- column : userid , point
2. 유저의 포인트 이상 인지 확인
- boolean : true or false
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loljoa.server.bet.config.GsonLocalDateTimeAdapter;
import com.loljoa.server.bet.domain.Account;
import com.loljoa.server.bet.dto.AccountDTO;
import com.loljoa.server.bet.dto.LeagueDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.loljoa.server.bet.domain.Contents.SCHEDULE_URL;

@Service
public class AccountAPI {
    private static RestTemplate restTemplate;
    public boolean removePoint(Long accountid, Long point){

        Gson gson = new Gson();

        // msa 간 통신
        String fullURL = SCHEDULE_URL + "api/account/" +accountid + "/point/"+point ;
        RestTemplate restTemplate = new RestTemplate();

        // class{ accountid , point}
        HttpEntity<String> entity = new HttpEntity<String>(gson.toJson(gson.toJson(new AccountDTO(accountid,point))));
        ResponseEntity<String> result = restTemplate.exchange(fullURL, HttpMethod.DELETE,entity,String.class);

        Map<String,String> map = gson.fromJson(result.getBody(), Map.class);
        return map.get("messages").equals("true") ? true : false;
    }
    public Account myAccount(String nickname){

        // msa 간 통신
        String fullURL = SCHEDULE_URL + "api/account/nickname/" + nickname ;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> result = restTemplate.getForEntity(fullURL,String.class);
        return new Gson().fromJson(result.getBody(), Account.class);
    }
}
