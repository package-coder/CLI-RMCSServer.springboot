package com.rmcs.accountableforms.afstate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AFStateRepository extends JpaRepository<AFState, String> {

}
