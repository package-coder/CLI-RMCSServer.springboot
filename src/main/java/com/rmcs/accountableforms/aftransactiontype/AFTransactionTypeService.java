package com.rmcs.accountableforms.aftransactiontype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AFTransactionTypeService {

    private final AFTransactionTypeRepository transactionTypeRepository;

    @Autowired
    public AFTransactionTypeService(AFTransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    public AFTransactionType getTransactionType(String id){
        return transactionTypeRepository.findById(id).get();
    }

    public AFTransactionType getTransactionTypeByEnum(AFTransactionTypeEnum typeEnum){
        return getTransactionType(typeEnum.getId());
    }

    public AFTransactionType getTransactionTypeByName(String name){
        return transactionTypeRepository.findByName(name).get();
    }
}
