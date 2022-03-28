package com.rmcs.accountableforms.afprefix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AFPrefixService {

    private final AFPrefixRepository prefixRepository;

    @Autowired
    public AFPrefixService(AFPrefixRepository prefixRepository) {
        this.prefixRepository = prefixRepository;
    }

    public AFPrefix getPrefix(String id){
        return prefixRepository.findById(id).get();
    }

    public AFPrefix getPrefixByEnum(AFPrefixEnum prefixEnum){
        return getPrefix(prefixEnum.getId());
    }
}
