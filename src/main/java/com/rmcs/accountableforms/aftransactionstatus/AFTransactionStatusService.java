package com.rmcs.accountableforms.aftransactionstatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AFTransactionStatusService {

    private final AFTransactionStatusRepository transactionStatusRepository;

    @Autowired
    public AFTransactionStatusService(AFTransactionStatusRepository transactionStatusRepository) {
        this.transactionStatusRepository = transactionStatusRepository;
    }

    public AFTransactionStatus getTransactionStatus(String id){
        return transactionStatusRepository.findById(id).get();
    }

    public AFTransactionStatus getTransactionStatusByEnum(AFTransactionStatusEnum statusEnum){
        return getTransactionStatus(statusEnum.getId());
    }
}
