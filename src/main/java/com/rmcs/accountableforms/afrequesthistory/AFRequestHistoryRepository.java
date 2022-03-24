package com.rmcs.accountableforms.afrequesthistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AFRequestHistoryRepository extends JpaRepository<AFRequestHistory, UUID> {
}
