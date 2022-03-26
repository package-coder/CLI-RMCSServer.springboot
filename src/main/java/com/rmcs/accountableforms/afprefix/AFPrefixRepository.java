package com.rmcs.accountableforms.afprefix;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AFPrefixRepository extends JpaRepository<AFPrefix, String> {
}
