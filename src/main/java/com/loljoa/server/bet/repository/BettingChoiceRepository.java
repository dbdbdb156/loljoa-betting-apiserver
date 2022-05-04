package com.loljoa.server.bet.repository;

import com.loljoa.server.bet.domain.BettingChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BettingChoiceRepository extends JpaRepository<BettingChoice, Long> {

}
