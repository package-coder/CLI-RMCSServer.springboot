package com.rmcs.accountableforms.aftype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AFTypeRepository extends JpaRepository<AFType, String> {}
