package com.rmcs.accountableforms.aftranasctionhistory;

import com.rmcs.accountableforms.afprefix.AFPrefix;
import com.rmcs.accountableforms.afprefix.AFPrefixEnum;
import com.rmcs.accountableforms.afprefix.AFPrefixRepository;
import com.rmcs.accountableforms.afrequesthistory.AFRequestHistory;
import com.rmcs.accountableforms.afrequesthistory.AFRequestHistoryRepository;
import com.rmcs.accountableforms.afrequestitem.AFRequestItem;
import com.rmcs.accountableforms.aftransactionitem.AFTransactionItem;
import com.rmcs.accountableforms.aftransactionstatus.AFTransactionStatus;
import com.rmcs.accountableforms.aftransactionstatus.AFTransactionStatusEnum;
import com.rmcs.accountableforms.aftransactionstatus.AFTransactionStatusRepository;
import com.rmcs.accountableforms.aftransactiontype.AFTransactionType;
import com.rmcs.accountableforms.aftransactiontype.AFTransactionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AFTransactionHistoryService {
    private final AFTransactionHistoryRepository transactionHistoryRepository;
    private final AFTransactionStatusRepository transactionStatusRepository;
    private final AFPrefixRepository prefixRepository;
    private final AFRequestHistoryRepository requestHistoryRepository;

    @Autowired
    public AFTransactionHistoryService(AFTransactionHistoryRepository transactionHistoryRepository,
                                       AFTransactionStatusRepository transactionStatusRepository,
                                       AFPrefixRepository prefixRepository,
                                       AFRequestHistoryRepository requestHistoryRepository) {

        this.transactionHistoryRepository = transactionHistoryRepository;
        this.transactionStatusRepository = transactionStatusRepository;
        this.prefixRepository = prefixRepository;
        this.requestHistoryRepository = requestHistoryRepository;
    }

    public List<AFTransactionHistory> getAllTransactionHistory(){
        return transactionHistoryRepository.findAll();
    }

    public AFTransactionHistory getTransactionHistory(UUID id){
        Optional<AFTransactionHistory> transactionHistoryOptional = transactionHistoryRepository.findById(id);
        boolean isObjectNotExist = transactionHistoryOptional.isEmpty();

        if(isObjectNotExist){
            throw new IllegalArgumentException("This transactionHistory: " + id + " not found");
        }

        return transactionHistoryOptional.get();
    }

    public AFTransactionHistory addTransactionHistory(AFTransactionHistory transactionHistory){
        var requestHistory = getRequestHistory(transactionHistory);

        if(requestHistory == null && transactionHistory.getTransactionType() == null )
            throw new IllegalArgumentException("AFTransactionType & AFRequestHistory must not same null");
        if(requestHistory == null && transactionHistory.getTransactionItems() == null)
            throw new IllegalArgumentException("List of AFTransactionItem must not null");
        if(requestHistory != null && transactionHistory.getApprovedRequestItems() == null)
            throw new IllegalArgumentException("ApprovedRequestItems(List of UUIDs) must not null");

        var transactionType = getTransactionType(transactionHistory, requestHistory);
        var transactionItems = getTransactionItems(transactionHistory, requestHistory);
        var transactionStatus = getStatusByEnum(AFTransactionStatusEnum.COMPLETED);

        transactionHistory.setTransactionItems(transactionItems);
        transactionHistory.setTransactionStatus(transactionStatus);
        transactionHistory.setTransactionType(transactionType);
        transactionHistory.setPrefix(getPrefixByEnum(AFPrefixEnum.TRANSACTION));

        for (var transactionItem : transactionItems) {

            if(AFTransactionTypeEnum.PURCHASE.equalByName(transactionType.getId()))
                transactionItem.getItemEntry().setTransactionItem(transactionItem);
            else
                transactionItem.setItemEntry(null);

            transactionItem.setStatus(transactionStatus);
            transactionItem.setTransactionHistory(transactionHistory);

            if(requestHistory != null)
                transactionItem.getRequestItem().setStatus(transactionStatus);
        }

        System.out.println(transactionHistory.getTransactionType());

        return transactionHistoryRepository.save(transactionHistory);
    }


    private AFTransactionStatus getStatusByEnum(AFTransactionStatusEnum statusEnum){
        return transactionStatusRepository.findById(statusEnum.getId()).get();
    }

    private AFPrefix getPrefixByEnum(AFPrefixEnum prefixEnum){
        return prefixRepository.findById(prefixEnum.getId()).get();
    }

    private List<AFTransactionItem> getTransactionItems(AFTransactionHistory transactionHistory, AFRequestHistory requestHistory){
        if(requestHistory == null)
            return transactionHistory.getTransactionItems();

        var requestItems = requestHistory.getRequestItems();
        var approvedRequestItems = transactionHistory.getApprovedRequestItems();

        return requestItems.stream()
                .filter(item -> requestExistInApprovedList(approvedRequestItems, item))
                .map(item -> new AFTransactionItem(item,
                        transactionHistory,
                        item.getType(),
                        item.getStatus(),
                        item.getQuantity(),
                        item.getItemEntry()))
                .toList();
    }

    private AFTransactionType getTransactionType(AFTransactionHistory transactionHistory, AFRequestHistory requestHistory){

        var transactionType = (requestHistory == null) ?
                transactionHistory.getTransactionType() :
                requestHistory.getTransactionType();

        var transactionTypeId = AFTransactionTypeEnum.of(transactionType.getId()).getId();
        transactionType.setId(transactionTypeId);
        return transactionType;
    }

    private AFRequestHistory getRequestHistory(AFTransactionHistory transactionHistory){
        if(transactionHistory.getRequestHistory() == null)
            return null;

        return requestHistoryRepository.findById(transactionHistory.getRequestHistory().getId()).orElse(null);
    }

    private boolean requestExistInApprovedList(List<AFRequestItem> approvedRequestItems, AFRequestItem requestItem){
        if(approvedRequestItems.isEmpty())
            return true;

        return approvedRequestItems.stream()
                .anyMatch(item -> item.getId().equals(requestItem.getId()));
    }
}
