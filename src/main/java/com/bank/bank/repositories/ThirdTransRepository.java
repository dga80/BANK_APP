package com.bank.bank.repositories;

import com.bank.bank.models.TransactionThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdTransRepository extends JpaRepository <TransactionThirdParty,Integer>{
}
