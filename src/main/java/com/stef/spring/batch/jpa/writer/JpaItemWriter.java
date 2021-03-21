package com.stef.spring.batch.jpa.writer;

import com.stef.spring.batch.jpa.exception.IDFourExceptionWriter;
import com.stef.spring.batch.jpa.model.PaysFrancais;
import com.stef.spring.batch.jpa.repository.PaysFrancaisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Slf4j
public class JpaItemWriter implements ItemWriter< PaysFrancais > {
    @Autowired
    private PaysFrancaisRepository respository;

    @Override
    public void write(List< ? extends PaysFrancais > list) throws Exception {
        log.info("Nombre d'éléments en entrée de writer: {}",list.size());
       for(PaysFrancais element : list){
           if(element.getId() == 4){
                   throw new IDFourExceptionWriter("Erreur volontaire sur id {0} pendant le writer",element.getId());
           }
       }
        respository.saveAll(list);
    }
}

