package com.rmcs.accountableforms.afrequesthistory;


import com.rmcs.accountableforms.afrequestitem.AFRequestItemRepository;
import com.rmcs.accountableforms.aftransactionstatus.AFTransactionStatusEnum;
import com.rmcs.accountableforms.aftransactionstatus.AFTransactionStatusRepository;
import com.rmcs.accountableforms.aftransactiontype.AFTransactionTypeEnum;
import com.rmcs.accountableforms.aftype.AFType;
import com.rmcs.accountableforms.aftype.AFTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AFRequestHistoryService {

    private final  AFRequestHistoryRepository requestHistoryRepository;
    private final AFTypeRepository typeRepository;
    private final AFRequestItemRepository requestItemRepository;
    private final AFTransactionStatusRepository transactionStatusRepository;

    @Autowired
    public AFRequestHistoryService(AFRequestHistoryRepository requestHistoryRepository,
                                   AFTypeRepository typeRepository,
                                   AFRequestItemRepository requestItemRepository,
                                   AFTransactionStatusRepository transactionStatusRepository) {

        this.requestHistoryRepository = requestHistoryRepository;
        this.typeRepository = typeRepository;
        this.requestItemRepository = requestItemRepository;
        this.transactionStatusRepository = transactionStatusRepository;
    }


    public List<AFRequestHistory> getAllRequestHistory() {
        return requestHistoryRepository.findAll();
    }


    public AFRequestHistory getRequestHistory(UUID requestHistoryId) {
        Optional<AFRequestHistory> requestHistoryOptional = requestHistoryRepository.findById(requestHistoryId);
        boolean isObjectNotExist = requestHistoryOptional.isEmpty();

        if(isObjectNotExist){
            throw new IllegalArgumentException("This requestHistoryId: " + requestHistoryId + " not found");
        }

        return requestHistoryOptional.get();
    }


    public AFRequestHistory addRequestHistory(AFRequestHistory requestHistory) {


        requestHistory.getTransactionStatus().setId(AFTransactionStatusEnum.PENDING.getId());

        var transactionType = requestHistory.getTransactionType();
        var transactionTypeEnum = AFTransactionTypeEnum.of(transactionType.getId());
        requestHistory.getTransactionType().setId(transactionTypeEnum.getId());


        //Check if each request items accountable form types is present
        for (var requestItem : requestHistory.getRequestItems()) {
            var type = requestItem.getType();
            Optional<AFType> typeOptional = typeRepository.findById(type.getId());
            boolean isTypeNotExist = typeOptional.isEmpty();

            if(isTypeNotExist)
                throw new IllegalArgumentException("AFType.id: " + type.getId() + " not exist");

            requestItem.setType(typeOptional.get());
            requestItem.setRequestHistory(requestHistory);
        }

        return requestHistoryRepository.save(requestHistory);
    }

}
