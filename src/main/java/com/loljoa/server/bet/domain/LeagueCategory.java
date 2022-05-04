package com.loljoa.server.bet.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class LeagueCategory {
    @Id
    @GeneratedValue
    private Long categoryId;

    String name;

    @OneToMany(mappedBy = "category")
    private List<League> leagues = new ArrayList<>();

    @OneToMany(mappedBy = "leagueCategory")
    private List<Post> posts = new ArrayList<>();

    public LeagueCategory(String name) {
        this.name = name;
    }
}
