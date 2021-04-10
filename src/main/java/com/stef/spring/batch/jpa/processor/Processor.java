package com.stef.spring.batch.jpa.processor;

import com.stef.spring.batch.jpa.exception.IDThreeExceptionProcessor;
import com.stef.spring.batch.jpa.mapping.PaysFancaisMapper;
import com.stef.spring.batch.jpa.model.Pays;
import com.stef.spring.batch.jpa.model.PaysFrancais;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor< Pays, PaysFrancais > {
    @Autowired
    PaysFancaisMapper paysFancaisMapper;
    @Override
    public PaysFrancais process(Pays pays) throws Exception {
        if(pays.getId() == 3){
            throw new IDThreeExceptionProcessor("Erreur volontaire sur l id {0} en processor",pays.getId());
        }
         return paysFancaisMapper.toPaysFrancais(pays);
    }
}
