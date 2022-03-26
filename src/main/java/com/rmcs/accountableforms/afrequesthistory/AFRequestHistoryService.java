package com.rmcs.accountableforms.afrequesthistory;


import com.rmcs.accountableforms.afprefix.AFPrefix;
import com.rmcs.accountableforms.afprefix.AFPrefixEnum;
import com.rmcs.accountableforms.afprefix.AFPrefixRepository;
import com.rmcs.accountableforms.aftransactionstatus.AFTransactionStatus;
import com.rmcs.accountableforms.aftransactionstatus.AFTransactionStatusEnum;
import com.rmcs.accountableforms.aftransactionstatus.AFTransactionStatusRepository;
import com.rmcs.accountableforms.aftransactiontype.AFTransactionType;
import com.rmcs.accountableforms.aftransactiontype.AFTransactionTypeEnum;
import com.rmcs.accountableforms.aftransactiontype.AFTransactionTypeRepository;
import com.rmcs.accountableforms.aftype.AFTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AFRequestHistoryService {

    private static final int DEFAULT_STUB_NUMBER = 50;

    private final AFRequestHistoryRepository requestHistoryRepository;
    private final AFTypeRepository typeRepository;
    private final AFTransactionStatusRepository transactionStatusRepository;
    private final AFPrefixRepository prefixRepository;
    private final AFTransactionTypeRepository transactionTypeRepository;

    @Autowired
    public AFRequestHistoryService(AFRequestHistoryRepository requestHistoryRepository,
                                   AFTypeRepository typeRepository,
                                   AFTransactionStatusRepository transactionStatusRepository,
                                   AFPrefixRepository prefixRepository,
                                   AFTransactionTypeRepository transactionTypeRepository) {

        this.requestHistoryRepository = requestHistoryRepository;
        this.typeRepository = typeRepository;
        this.transactionStatusRepository = transactionStatusRepository;
        this.prefixRepository = prefixRepository;
        this.transactionTypeRepository = transactionTypeRepository;
    }


    public List<AFRequestHistory> getAllRequestHistory() {
        return requestHistoryRepository.findAll();
    }


    public AFRequestHistory getRequestHistory(UUID id) {
        Optional<AFRequestHistory> requestHistoryOptional = requestHistoryRepository.findById(id);
        boolean isObjectNotExist = requestHistoryOptional.isEmpty();

        if(isObjectNotExist){
            throw new IllegalArgumentException("This requestHistoryId: " + id + " not found");
        }

        return requestHistoryOptional.get();
    }


    public AFRequestHistory addRequestHistory(AFRequestHistory requestHistory) {
        //TODO code formatting

        System.out.println(requestHistory);
        var transactionTypeId = requestHistory.getTransactionType().getId();

        //Set the default values
        requestHistory.setTransactionType(getTransactionTypeById(transactionTypeId));
        requestHistory.setTransactionStatus(getStatusByEnum(AFTransactionStatusEnum.PENDING));
        requestHistory.setPrefix(getPrefixByEnum(AFPrefixEnum.REQUEST));

        //Check if each request items accountable form types is present
        for (var requestItem : requestHistory.getRequestItems()) {
            requestItem.setStatus(getStatusByEnum(AFTransactionStatusEnum.PENDING));
            requestItem.setRequestHistory(requestHistory);

            //Set the entry if the type of transaction is purchase else null
            if(AFTransactionTypeEnum.PURCHASE.equalByName(transactionTypeId)){
                var itemEntry = requestItem.getItemEntry();
                var quantity = requestItem.getQuantity();
                var startSeries = itemEntry.getStartSeries();
                var startStub = itemEntry.getStartStub();

                itemEntry.setRequestItem(requestItem);
                itemEntry.setTransactionItem(null);
                itemEntry.setEndSeries(calculateEntryEndSeries(startSeries));
                itemEntry.setEndStub(calculateEntryEndStub(startStub, quantity));
            }
            else {
                requestItem.setItemEntry(null);
            }
        }

        return requestHistoryRepository.save(requestHistory);
    }

    private AFTransactionStatus getStatusByEnum(AFTransactionStatusEnum statusEnum){
        return transactionStatusRepository.findById(statusEnum.getId()).get();
    }

    private AFPrefix getPrefixByEnum(AFPrefixEnum prefixEnum){
        return prefixRepository.findById(prefixEnum.getId()).get();
    }

    private AFTransactionType getTransactionTypeById(String name){
        var transactionTypeOptional = transactionTypeRepository.findByName(name);
        boolean isTypeNotExist = transactionTypeOptional.isEmpty();

        if(isTypeNotExist)
            throw new IllegalArgumentException("AFTransactionType (id=" + name + ") not exist");

        return transactionTypeOptional.get();
    }

    private Integer calculateEntryEndSeries(Integer startSeries){
        return DEFAULT_STUB_NUMBER + startSeries - 1;
    }

    private Integer calculateEntryEndStub(Integer startStub, Integer quantity){
        return startStub + quantity - 1;
    }
}
