package com.rmcs.accountableforms.aftranasctionhistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AFTransactionHistoryRepository extends JpaRepository<AFTransactionHistory, UUID> {
}
