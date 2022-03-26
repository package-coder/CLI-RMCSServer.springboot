package com.rmcs.accountableforms.aftransactiontype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AFTransactionTypeRepository extends JpaRepository<AFTransactionType, String> {

    Optional<AFTransactionType> findByName(String name);
}
