package com.rmcs.accountableforms.aftransactionitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AFTransactionItemRepository extends JpaRepository<AFTransactionItem, UUID> {
}
