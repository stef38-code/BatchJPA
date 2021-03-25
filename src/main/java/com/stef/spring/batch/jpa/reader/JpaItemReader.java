package com.stef.spring.batch.jpa.reader;

import com.stef.spring.batch.jpa.exception.IDTwoExceptionReader;
import com.stef.spring.batch.jpa.exception.OtherException;
import com.stef.spring.batch.jpa.model.Pays;
import com.stef.spring.batch.jpa.repository.PaysRepository;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Objects;

/**
 * Cette classe va aller lire en db les elements à traiter
 */
@Component
public class JpaItemReader implements ItemReader< Pays > {
    @Autowired
    private  PaysRepository respository;
    /**
     * Liste des elements à traiter
     */
    private Iterator<Pays> rowsIterator;

    @BeforeStep
    public void before(StepExecution stepExecution) {
        //recherche tout les elements
        rowsIterator = respository.findAll().iterator();
    }

    @Override
    public Pays read() throws Exception {
        //retourne 1 element après l'autres
        if (Objects.nonNull(rowsIterator ) && rowsIterator.hasNext()) {
            Pays element = rowsIterator.next();
            if(element.getId() == 2){
                throw new IDTwoExceptionReader("Erreur volontaire sur id: {0}",element.getId());
            }
            if(element.getId() % 4 == 0){
                throw new OtherException("Erreur aléatoire sur id: {0}",element.getId());
            }
            return element;
        } else {
            return null;
        }
    }
}
