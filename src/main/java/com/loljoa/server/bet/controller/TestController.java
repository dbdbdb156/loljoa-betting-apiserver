package com.loljoa.server.bet.controller;

import com.google.gson.*;
import com.loljoa.server.bet.api.LeagueAPI;
import com.loljoa.server.bet.config.GsonLocalDateTimeAdapter;
import com.loljoa.server.bet.domain.Account;
import com.loljoa.server.bet.domain.BettingGame;
import com.loljoa.server.bet.domain.League;
import com.loljoa.server.bet.dto.LeagueDTO;
import com.loljoa.server.bet.repository.AccountRepository;
import com.loljoa.server.bet.repository.GameDataRepository;
import com.loljoa.server.bet.repository.LeagueRepository;
import com.loljoa.server.bet.service.BettingChoiceService;
import com.loljoa.server.bet.service.BettingStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Controller
public class TestController {

    private String temp = "{\"category\":\"lck\",\"schedules\":[{\"id\":21,\"leagueName\":\"HLEvsBRO\",\"weekNum\":\"Week 1\",\"startTime\":\"2022-01-16 05:00\",\"endTime\":null},{\"id\":29,\"leagueName\":\"DKvsBRO\",\"weekNum\":\"Week 2\",\"startTime\":\"2022-01-22 05:00\",\"endTime\":null},{\"id\":35,\"leagueName\":\"DRXvsBRO\",\"weekNum\":\"Week 3\",\"startTime\":\"2022-01-26 08:00\",\"endTime\":null},{\"id\":52,\"leagueName\":\"T1vsBRO\",\"weekNum\":\"Week 8\",\"startTime\":\"2022-03-13 05:00\",\"endTime\":null},{\"id\":55,\"leagueName\":\"GENvsBRO\",\"weekNum\":\"Week 9\",\"startTime\":\"2022-03-16 08:00\",\"endTime\":null},{\"id\":77,\"leagueName\":\"NSvsBRO\",\"weekNum\":\"Week 4\",\"startTime\":\"2022-02-11 05:00\",\"endTime\":null},{\"id\":83,\"leagueName\":\"LSBvsBRO\",\"weekNum\":\"Week 5\",\"startTime\":\"2022-02-16 05:00\",\"endTime\":null},{\"id\":90,\"leagueName\":\"KDFvsBRO\",\"weekNum\":\"Week 5\",\"startTime\":\"2022-02-19 08:00\",\"endTime\":null},{\"id\":99,\"leagueName\":\"KTvsBRO\",\"weekNum\":\"Week 6\",\"startTime\":\"2022-02-26 05:00\",\"endTime\":null},{\"id\":14,\"leagueName\":\"KTvsDK\",\"weekNum\":\"Week 1\",\"startTime\":\"2022-01-13 08:00\",\"endTime\":null},{\"id\":23,\"leagueName\":\"NSvsDK\",\"weekNum\":\"Week 2\",\"startTime\":\"2022-01-19 05:00\",\"endTime\":null},{\"id\":38,\"leagueName\":\"HLEvsDK\",\"weekNum\":\"Week 3\",\"startTime\":\"2022-01-28 05:00\",\"endTime\":null},{\"id\":45,\"leagueName\":\"GENvsDK\",\"weekNum\":\"Week 8\",\"startTime\":\"2022-03-09 08:00\",\"endTime\":null},{\"id\":56,\"leagueName\":\"DRXvsDK\",\"weekNum\":\"Week 9\",\"startTime\":\"2022-03-17 05:00\",\"endTime\":null},{\"id\":61,\"leagueName\":\"BROvsDK\",\"weekNum\":\"Week 9\",\"startTime\":\"2022-03-19 08:00\",\"endTime\":null},{\"id\":80,\"leagueName\":\"LSBvsDK\",\"weekNum\":\"Week 4\",\"startTime\":\"2022-02-12 08:00\",\"endTime\":null},{\"id\":94,\"leagueName\":\"T1vsDK\",\"weekNum\":\"Week 6\",\"startTime\":\"2022-02-23 08:00\",\"endTime\":null},{\"id\":98,\"leagueName\":\"KDFvsDK\",\"weekNum\":\"Week 6\",\"startTime\":\"2022-02-25 08:00\",\"endTime\":null},{\"id\":16,\"leagueName\":\"GENvsDRX\",\"weekNum\":\"Week 1\",\"startTime\":\"2022-01-14 05:00\",\"endTime\":null},{\"id\":32,\"leagueName\":\"KDFvsDRX\",\"weekNum\":\"Week 2\",\"startTime\":\"2022-01-23 08:00\",\"endTime\":null},{\"id\":41,\"leagueName\":\"NSvsDRX\",\"weekNum\":\"Week 3\",\"startTime\":\"2022-01-29 08:00\",\"endTime\":null},{\"id\":63,\"leagueName\":\"T1vsDRX\",\"weekNum\":\"Week 9\",\"startTime\":\"2022-03-20 08:00\",\"endTime\":null},{\"id\":86,\"leagueName\":\"DKvsDRX\",\"weekNum\":\"Week 5\",\"startTime\":\"2022-02-17 08:00\",\"endTime\":null},{\"id\":89,\"leagueName\":\"HLEvsDRX\",\"weekNum\":\"Week 5\",\"startTime\":\"2022-02-19 05:00\",\"endTime\":null},{\"id\":95,\"leagueName\":\"BROvsDRX\",\"weekNum\":\"Week 6\",\"startTime\":\"2022-02-24 05:00\",\"endTime\":null},{\"id\":107,\"leagueName\":\"KTvsDRX\",\"weekNum\":\"Week 7\",\"startTime\":\"2022-03-04 05:00\",\"endTime\":null},{\"id\":111,\"leagueName\":\"LSBvsDRX\",\"weekNum\":\"Week 7\",\"startTime\":\"2022-03-06 05:00\",\"endTime\":null},{\"id\":22,\"leagueName\":\"DKvsGEN\",\"weekNum\":\"Week 1\",\"startTime\":\"2022-01-16 08:00\",\"endTime\":null},{\"id\":26,\"leagueName\":\"BROvsGEN\",\"weekNum\":\"Week 2\",\"startTime\":\"2022-01-20 08:00\",\"endTime\":null},{\"id\":51,\"leagueName\":\"HLEvsGEN\",\"weekNum\":\"Week 8\",\"startTime\":\"2022-03-12 08:00\",\"endTime\":null},{\"id\":59,\"leagueName\":\"KTvsGEN\",\"weekNum\":\"Week 9\",\"startTime\":\"2022-03-18 08:00\",\"endTime\":null},{\"id\":76,\"leagueName\":\"LSBvsGEN\",\"weekNum\":\"Week 4\",\"startTime\":\"2022-02-10 08:00\",\"endTime\":null},{\"id\":79,\"leagueName\":\"T1vsGEN\",\"weekNum\":\"Week 4\",\"startTime\":\"2022-02-12 05:00\",\"endTime\":null},{\"id\":84,\"leagueName\":\"NSvsGEN\",\"weekNum\":\"Week 5\",\"startTime\":\"2022-02-16 08:00\",\"endTime\":null},{\"id\":101,\"leagueName\":\"DRXvsGEN\",\"weekNum\":\"Week 6\",\"startTime\":\"2022-02-27 05:00\",\"endTime\":null},{\"id\":109,\"leagueName\":\"KDFvsGEN\",\"weekNum\":\"Week 7\",\"startTime\":\"2022-03-05 05:00\",\"endTime\":null},{\"id\":24,\"leagueName\":\"KTvsHLE\",\"weekNum\":\"Week 2\",\"startTime\":\"2022-01-19 08:00\",\"endTime\":null},{\"id\":30,\"leagueName\":\"GENvsHLE\",\"weekNum\":\"Week 2\",\"startTime\":\"2022-01-22 08:00\",\"endTime\":null},{\"id\":57,\"leagueName\":\"KDFvsHLE\",\"weekNum\":\"Week 9\",\"startTime\":\"2022-03-17 08:00\",\"endTime\":null},{\"id\":60,\"leagueName\":\"NSvsHLE\",\"weekNum\":\"Week 9\",\"startTime\":\"2022-03-19 05:00\",\"endTime\":null},{\"id\":73,\"leagueName\":\"T1vsHLE\",\"weekNum\":\"Week 4\",\"startTime\":\"2022-02-09 05:00\",\"endTime\":null},{\"id\":78,\"leagueName\":\"DRXvsHLE\",\"weekNum\":\"Week 4\",\"startTime\":\"2022-02-11 08:00\",\"endTime\":null},{\"id\":96,\"leagueName\":\"LSBvsHLE\",\"weekNum\":\"Week 6\",\"startTime\":\"2022-02-24 08:00\",\"endTime\":null},{\"id\":103,\"leagueName\":\"BROvsHLE\",\"weekNum\":\"Week 7\",\"startTime\":\"2022-03-02 05:00\",\"endTime\":null},{\"id\":108,\"leagueName\":\"DKvsHLE\",\"weekNum\":\"Week 7\",\"startTime\":\"2022-03-04 08:00\",\"endTime\":null},{\"id\":8,\"leagueName\":\"T1vsKDF\",\"weekNum\":\"Week 1\",\"startTime\":\"2022-01-12 08:00\",\"endTime\":null},{\"id\":27,\"leagueName\":\"LSBvsKDF\",\"weekNum\":\"Week 2\",\"startTime\":\"2022-01-21 05:00\",\"endTime\":null},{\"id\":33,\"leagueName\":\"BROvsKDF\",\"weekNum\":\"Tiebreakers\",\"startTime\":\"2022-03-20 05:00\",\"endTime\":null},{\"id\":34,\"leagueName\":\"GENvsKDF\",\"weekNum\":\"Week 3\",\"startTime\":\"2022-01-26 05:00\",\"endTime\":null},{\"id\":39,\"leagueName\":\"BROvsKDF\",\"weekNum\":\"Week 3\",\"startTime\":\"2022-01-28 08:00\",\"endTime\":null},{\"id\":53,\"leagueName\":\"DRXvsKDF\",\"weekNum\":\"Week 8\",\"startTime\":\"2022-03-13 08:00\",\"endTime\":null},{\"id\":75,\"leagueName\":\"DKvsKDF\",\"weekNum\":\"Week 4\",\"startTime\":\"2022-02-10 05:00\",\"endTime\":null},{\"id\":85,\"leagueName\":\"HLEvsKDF\",\"weekNum\":\"Week 5\",\"startTime\":\"2022-02-17 05:00\",\"endTime\":null},{\"id\":93,\"leagueName\":\"KTvsKDF\",\"weekNum\":\"Week 6\",\"startTime\":\"2022-02-23 05:00\",\"endTime\":null},{\"id\":106,\"leagueName\":\"NSvsKDF\",\"weekNum\":\"Week 7\",\"startTime\":\"2022-03-03 08:00\",\"endTime\":null},{\"id\":20,\"leagueName\":\"KDFvsKT\",\"weekNum\":\"Week 1\",\"startTime\":\"2022-01-15 08:00\",\"endTime\":null},{\"id\":40,\"leagueName\":\"GENvsKT\",\"weekNum\":\"Week 3\",\"startTime\":\"2022-01-29 05:00\",\"endTime\":null},{\"id\":46,\"leagueName\":\"HLEvsKT\",\"weekNum\":\"Week 8\",\"startTime\":\"2022-03-10 05:00\",\"endTime\":null},{\"id\":50,\"leagueName\":\"LSBvsKT\",\"weekNum\":\"Week 8\",\"startTime\":\"2022-03-12 05:00\",\"endTime\":null},{\"id\":54,\"leagueName\":\"NSvsKT\",\"weekNum\":\"Week 9\",\"startTime\":\"2022-03-16 05:00\",\"endTime\":null},{\"id\":74,\"leagueName\":\"DRXvsKT\",\"weekNum\":\"Week 4\",\"startTime\":\"2022-02-09 08:00\",\"endTime\":null},{\"id\":82,\"leagueName\":\"BROvsKT\",\"weekNum\":\"Week 4\",\"startTime\":\"2022-02-13 08:00\",\"endTime\":null},{\"id\":91,\"leagueName\":\"DKvsKT\",\"weekNum\":\"Week 5\",\"startTime\":\"2022-02-20 05:00\",\"endTime\":null},{\"id\":112,\"leagueName\":\"T1vsKT\",\"weekNum\":\"Week 7\",\"startTime\":\"2022-03-06 08:00\",\"endTime\":null},{\"id\":5,\"leagueName\":\"DRXvsLSB\",\"weekNum\":\"Week 1\",\"startTime\":\"2022-01-12 05:00\",\"endTime\":null},{\"id\":19,\"leagueName\":\"NSvsLSB\",\"weekNum\":\"Week 1\",\"startTime\":\"2022-01-15 05:00\",\"endTime\":null},{\"id\":36,\"leagueName\":\"KTvsLSB\",\"weekNum\":\"Week 3\",\"startTime\":\"2022-01-27 05:00\",\"endTime\":null},{\"id\":43,\"leagueName\":\"HLEvsLSB\",\"weekNum\":\"Week 3\",\"startTime\":\"2022-01-30 08:00\",\"endTime\":null},{\"id\":47,\"leagueName\":\"BROvsLSB\",\"weekNum\":\"Week 8\",\"startTime\":\"2022-03-10 08:00\",\"endTime\":null},{\"id\":58,\"leagueName\":\"T1vsLSB\",\"weekNum\":\"Week 9\",\"startTime\":\"2022-03-18 05:00\",\"endTime\":null},{\"id\":62,\"leagueName\":\"KDFvsLSB\",\"weekNum\":\"Week 9\",\"startTime\":\"2022-03-20 05:00\",\"endTime\":null},{\"id\":88,\"leagueName\":\"GENvsLSB\",\"weekNum\":\"Week 5\",\"startTime\":\"2022-02-18 08:00\",\"endTime\":null},{\"id\":104,\"leagueName\":\"DKvsLSB\",\"weekNum\":\"Week 7\",\"startTime\":\"2022-03-02 08:00\",\"endTime\":null},{\"id\":11,\"leagueName\":\"HLEvsNS\",\"weekNum\":\"Week 1\",\"startTime\":\"2022-01-13 05:00\",\"endTime\":null},{\"id\":28,\"leagueName\":\"KTvsNS\",\"weekNum\":\"Week 2\",\"startTime\":\"2022-01-21 08:00\",\"endTime\":null},{\"id\":37,\"leagueName\":\"T1vsNS\",\"weekNum\":\"Week 3\",\"startTime\":\"2022-01-27 08:00\",\"endTime\":null},{\"id\":44,\"leagueName\":\"DRXvsNS\",\"weekNum\":\"Week 8\",\"startTime\":\"2022-03-09 05:00\",\"endTime\":null},{\"id\":49,\"leagueName\":\"DKvsNS\",\"weekNum\":\"Week 8\",\"startTime\":\"2022-03-11 08:00\",\"endTime\":null},{\"id\":81,\"leagueName\":\"KDFvsNS\",\"weekNum\":\"Week 4\",\"startTime\":\"2022-02-13 05:00\",\"endTime\":null},{\"id\":97,\"leagueName\":\"GENvsNS\",\"weekNum\":\"Week 6\",\"startTime\":\"2022-02-25 05:00\",\"endTime\":null},{\"id\":102,\"leagueName\":\"LSBvsNS\",\"weekNum\":\"Week 6\",\"startTime\":\"2022-02-27 08:00\",\"endTime\":null},{\"id\":110,\"leagueName\":\"BROvsNS\",\"weekNum\":\"Week 7\",\"startTime\":\"2022-03-05 08:00\",\"endTime\":null},{\"id\":18,\"leagueName\":\"BROvsT1\",\"weekNum\":\"Week 1\",\"startTime\":\"2022-01-14 08:00\",\"endTime\":null},{\"id\":25,\"leagueName\":\"DRXvsT1\",\"weekNum\":\"Week 2\",\"startTime\":\"2022-01-20 05:00\",\"endTime\":null},{\"id\":31,\"leagueName\":\"LSBvsT1\",\"weekNum\":\"Week 2\",\"startTime\":\"2022-01-23 05:00\",\"endTime\":null},{\"id\":42,\"leagueName\":\"DKvsT1\",\"weekNum\":\"Week 3\",\"startTime\":\"2022-01-30 05:00\",\"endTime\":null},{\"id\":48,\"leagueName\":\"KDFvsT1\",\"weekNum\":\"Week 8\",\"startTime\":\"2022-03-11 05:00\",\"endTime\":null},{\"id\":87,\"leagueName\":\"KTvsT1\",\"weekNum\":\"Week 5\",\"startTime\":\"2022-02-18 05:00\",\"endTime\":null},{\"id\":92,\"leagueName\":\"NSvsT1\",\"weekNum\":\"Week 5\",\"startTime\":\"2022-02-20 08:00\",\"endTime\":null},{\"id\":100,\"leagueName\":\"HLEvsT1\",\"weekNum\":\"Week 6\",\"startTime\":\"2022-02-26 08:00\",\"endTime\":null},{\"id\":105,\"leagueName\":\"GENvsT1\",\"weekNum\":\"Week 7\",\"startTime\":\"2022-03-03 05:00\",\"endTime\":null}]}";

    private final AccountRepository accountRepository;
    private final GameDataRepository gameDataRepository;
    private final LeagueRepository leagueRepository;
    private final BettingStateService bettingStateService;
    private final LeagueAPI leagueAPI ;

    private final BettingChoiceService bettingChoiceService;

    // Lazy Loading 이슈 : 해결 어려웠음 : 해결방법 - Value를 DTO 에 담아서 json 으로 보내면 됨.
//    @GetMapping("test/bet/all")
//    public ResponseEntity<String> betGame() throws JsonProcessingException {
        // Need item - 1.Account 2. Legue -> make BettingChoice + BettingState

 //       List<BettingChoice> choice = bettingChoiceService.findBettingChoices();
 //       for(int i=0;i<choice.size();++i)
//            System.out.println(choice.get(i).getBetTime());
//
//        List<BettingChoiceDTO> list = new ArrayList();
//        for(int i=0;i<choice.size();++i)
//            list.add(new BettingChoiceDTO(choice.get(i).getId(),choice.get(i).getPoint(),choice.get(i).getBetTime()));


   //     Gson gson = new GsonBuilder()
   //             //.setPrettyPrinting()
  //              .excludeFieldsWithoutExposeAnnotation()
  //              .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
//        return new ResponseEntity(gson.toJson(list),HttpStatus.OK);
 //   }


    @PostMapping("/bet/test")
    public ResponseEntity<String> bettingList(HttpServletRequest req, HttpServletResponse res
           )throws ServletException, IOException {

        //Account account = new Account(1L,"TestMan","admin",1000L);
        Long point = 100L;
        // Account 파라미터로 받아야함. Next Change Account.findOne
        Account account = accountRepository.findById(1L).get();
        // GameData 파라미터로 받아야함.
        BettingGame bettingGame = gameDataRepository.getById(2L);
        // leadata 받기
        String value =
                new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create()
                .toJson(new LeagueDTO(21L,"HLEvsBRO","Week 1",LocalDateTime.now(),null,4L));

        LeagueDTO leagueDTO = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                            throws JsonParseException {
                        return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    }
                }).create().fromJson(value,LeagueDTO.class);

        boolean enoughPoint = false;
        boolean applyChangePoint = false;

        if(bettingStateService.timeIsOver(leagueDTO.getStartTime())){
            enoughPoint = true;
            // 경기 시작전 배팅 가능하면
            if(bettingStateService.pointIsEnough(account.getPoint(),point)){
                applyChangePoint = true;
                account.removePoint(point);
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
