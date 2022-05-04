package com.loljoa.server.bet.service;

import com.loljoa.server.bet.domain.Account;
import com.loljoa.server.bet.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;

    public Long addAccount(String username, String password){
        Account account = new Account(username,password);
        accountRepository.save(account);
        return account.getAccountId();
    }

    public boolean removePoint(Account account,Long point){
        return account.removePoint(point);
    }

    @Transactional(readOnly = true)
    public Account findAccountByName(String username){
        return accountRepository.findByUsername(username);
    }

}
