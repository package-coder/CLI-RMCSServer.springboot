package com.rmcs.accountableforms.aftype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/accountable_form/types")
public class AFTypeController {

    private final AFTypeService service;

    @Autowired
    public AFTypeController(AFTypeService service) {
        this.service = service;
    }

    @GetMapping
    public List<AFType> getAllType(){
        return service.getAllType();
    }

    @PostMapping
    public AFType addType(@RequestBody AFType afType){
        return service.addType(afType);
    }

    @GetMapping("{id}")
    public AFType getType(@PathVariable String id){
        return service.getType(id);
    }
}
