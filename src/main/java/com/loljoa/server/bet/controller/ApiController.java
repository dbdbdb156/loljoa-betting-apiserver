package com.loljoa.server.bet.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loljoa.server.bet.dto.LeagueDTO;
import com.loljoa.server.bet.dto.ScheduleDto;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {

    @GetMapping("/schedule/all")
    public ResponseEntity<ScheduleDto> receiveData(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity("http://localhost:8081/schedule/all",ScheduleDto.class);
    }
    //
    @GetMapping("/schedule/all/test")
    public ResponseEntity<String> receiveDataTest(){
        // ip 할당
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null)
            ip = req.getRemoteAddr();


        System.out.println("TEST : "+ip+" "+req.getRequestURI());

        // msa 간 통신
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity("http://"+ip+":8081/schedule/all",String.class);

        // json 변환
        Gson gson = new Gson();
        ScheduleDto scheduleDto = gson.fromJson(result.getBody(), ScheduleDto.class);

        return result;
    }
}
