package com.bank.bank.repositories;

import com.bank.bank.models.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty,Integer> {
    List<ThirdParty> findThirdByHashedKey(String hashedKey);
}
