package com.bank.bank.repositories;

import com.bank.bank.models.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty,Integer> {
    Optional<ThirdParty> findByHashedKey(String hashedKey);
}
