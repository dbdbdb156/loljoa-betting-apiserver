package com.loljoa.server.bet.repository;

import com.loljoa.server.bet.domain.BettingGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDataRepository extends JpaRepository<BettingGame, Long> {
}
