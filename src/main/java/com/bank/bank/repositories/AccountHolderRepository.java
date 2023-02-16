package com.bank.bank.repositories;

import com.bank.bank.models.AccountHolder;
import com.bank.bank.models.ThirdParty;
import org.hibernate.cfg.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder,Integer> {

}
