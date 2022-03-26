package com.rmcs.accountableforms.aftransactionitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/accountable_form/transaction_items")
public class AFTransactionItemController {

    private final AFTransactionItemService service;

    @Autowired
    public AFTransactionItemController(AFTransactionItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<AFTransactionItem> getAllTransactionItem(){
        return service.getAllTransactionItem();
    }

    @GetMapping("{id}")
    public AFTransactionItem getTransactionItem(@PathVariable UUID id){
        return service.getTransactionItem(id);
    }
}
