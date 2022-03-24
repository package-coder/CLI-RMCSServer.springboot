package com.rmcs.accountableforms.aftransactiontype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AFTransactionTypeRepository extends JpaRepository<AFTransactionType, String> {
}
