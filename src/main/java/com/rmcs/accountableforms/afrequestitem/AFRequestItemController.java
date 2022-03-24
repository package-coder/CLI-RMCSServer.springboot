package com.rmcs.accountableforms.afrequestitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/accountable_form/request_items")
public class AFRequestItemController {

    private final AFRequestItemService service;

    @Autowired
    public AFRequestItemController(AFRequestItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<AFRequestItem> getAllRequestItem(){
        return service.getAllRequestItem();
    }

    @GetMapping("{id}")
    public AFRequestItem getRequestItem(@PathVariable UUID id){
        return service.getRequestItem(id);
    }
}
