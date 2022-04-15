package com.loljoa.server.bet.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    private Long accountId;

    @Column(unique = true)
    String username;
    String password;

    @Column(nullable = false)
    private Long point;

    @ManyToOne(targetEntity = Tier.class)
    private Tier tier;

    @OneToMany(mappedBy = "better", cascade = CascadeType.ALL)
    private List<BettingState> bettingStates = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "commenter")
    List<PostComment> postComments = new ArrayList<>();

    @OneToMany(mappedBy = "commenter")
    List<LeagueComment> leagueComments = new ArrayList<>();

    public Account(Long accountId) {
        this.accountId = accountId;
        this.point = 0L;
    }
    public Account(Long accountId, Long point){
        this.accountId = accountId;
        this.point = point;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.tier = new Tier("브론즈");
        this.point = 1000L;
    }

    public Account(String username, String password,Tier tier,Long point) {
        this.username = username;
        this.password = password;
        this.tier = tier;
        this.point = point;
    }

    public boolean removePoint(Long point){
        Long restPoint = this.point - point;
        if(restPoint < 0) return false;
        this.point = restPoint;
        return true;
    }

}
