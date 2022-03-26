package com.rmcs.accountableforms.aftranasctionhistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/accountable_form/transactions")
public class AFTransactionHistoryController {

    private final AFTransactionHistoryService service;

    @Autowired
    public AFTransactionHistoryController(AFTransactionHistoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<AFTransactionHistory> getAllTransactionHistory(){
        return service.getAllTransactionHistory();
    }

    @GetMapping("{id}")
    public AFTransactionHistory getTransactionHistory(@PathVariable UUID id){
        return service.getTransactionHistory(id);
    }

    @PostMapping
    public AFTransactionHistory addTransactionHistory(@RequestBody @Valid AFTransactionHistory transactionHistory){
        AFTransactionHistory afTransactionHistory = service.addTransactionHistory(transactionHistory);
        System.out.println(afTransactionHistory);

        return afTransactionHistory;
    }
}
