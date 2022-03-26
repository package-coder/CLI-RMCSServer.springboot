package com.rmcs.accountableforms.aftransactionstatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AFTransactionStatusRepository extends JpaRepository<AFTransactionStatus, String> {

}
