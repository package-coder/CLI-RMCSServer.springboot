package com.rmcs.accountableforms.afrequestitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AFRequestItemService {
    private final AFRequestItemRepository afRequestItemRepository;

    @Autowired
    public AFRequestItemService(AFRequestItemRepository afRequestItemRepository) {
        this.afRequestItemRepository = afRequestItemRepository;
    }


    public List<AFRequestItem> getAllRequestItem() {
        return afRequestItemRepository.findAll();
    }

    public AFRequestItem getRequestItem(UUID requestItemId) {
        Optional<AFRequestItem> requestItemOptional = afRequestItemRepository.findById(requestItemId);
        boolean isEmpty = requestItemOptional.isEmpty();

        if(isEmpty) throw new RuntimeException("This requestItemId: " + requestItemId + " not found");

        return requestItemOptional.get();
    }

    public AFRequestItem addRequestItem(AFRequestItem afRequestItem) {
        return afRequestItemRepository.save(afRequestItem);
    }
}
