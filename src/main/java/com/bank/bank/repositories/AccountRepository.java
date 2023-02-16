package com.bank.bank.repositories;

import com.bank.bank.models.Account;
import com.bank.bank.models.AccountHolder;
import com.bank.bank.models.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    List<Account> findByPrimaryOwner(AccountHolder accountHolder);

    //Account findByIdAndUser(Integer targetAccountId, ThirdParty thirdParty);
    Optional<Account> findBySecretKey(String secretKey);
}
