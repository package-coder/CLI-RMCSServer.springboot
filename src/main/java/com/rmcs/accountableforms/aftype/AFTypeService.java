package com.rmcs.accountableforms.aftype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AFTypeService {
    private final AFTypeRepository repository;

    @Autowired
    public AFTypeService(AFTypeRepository AFTypeRepository) {
        this.repository = AFTypeRepository;
    }

    public List<AFType> getAllType(){
        return repository.findAll();
    }

    public AFType addType(AFType afType){
        boolean exists = repository.existsById(afType.getId());

        if(exists)  throw new IllegalArgumentException("This id: " + afType.getId() + " exists already");

        return repository.save(afType);
    }

    public AFType getType(String typeId) {
        Optional<AFType> afTypeOptional = repository.findById(typeId);
        boolean isEmpty = afTypeOptional.isEmpty();

        if(isEmpty) throw new IllegalArgumentException("This id: " + typeId + " not found");

        return afTypeOptional.get();
    }
}
