package com.loljoa.server.bet.repository;


import com.loljoa.server.bet.domain.BettingState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BettingStateRepository extends JpaRepository<BettingState, Long> {

}
