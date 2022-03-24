package com.rmcs.accountableforms.afrequesthistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/accountable_form/requests")
public class AFRequestHistoryController {

    private final AFRequestHistoryService service;

    @Autowired
    public AFRequestHistoryController(AFRequestHistoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<AFRequestHistory> getAllRequestHistory(){
        return service.getAllRequestHistory();
    }

    @GetMapping("{id}")
    public AFRequestHistory getRequestHistory(@PathVariable UUID id){
        return service.getRequestHistory(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AFRequestHistory addRequestHistory(@RequestBody AFRequestHistory requestHistory){
        return service.addRequestHistory(requestHistory);
    }
}
