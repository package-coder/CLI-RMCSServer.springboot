package com.rmcs.accountableforms.aftransactionitem;

import com.rmcs.accountableforms.afrequestitem.AFRequestItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AFTransactionItemService {

    private final AFTransactionItemRepository afTransactionItemRepository;

    @Autowired
    public AFTransactionItemService(AFTransactionItemRepository afTransactionItemRepository) {
        this.afTransactionItemRepository = afTransactionItemRepository;
    }

    public List<AFTransactionItem> getAllTransactionItem(){
        return afTransactionItemRepository.findAll();
    }

    public AFTransactionItem getTransactionItem(@PathVariable UUID id){
        Optional<AFTransactionItem> transactionItemOptional = afTransactionItemRepository.findById(id);
        boolean isEmpty = transactionItemOptional.isEmpty();

        if(isEmpty) throw new RuntimeException("This transactionItemId: " + id + " not found");

        return transactionItemOptional.get();
    }
}
