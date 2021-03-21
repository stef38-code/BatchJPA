package com.stef.spring.batch.jpa.policy;

import com.stef.spring.batch.jpa.exception.IDFourExceptionWriter;
import com.stef.spring.batch.jpa.exception.IDThreeExceptionProcessor;
import com.stef.spring.batch.jpa.exception.IDTwoExceptionReader;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

/**
 * Permet d'eviter un arrêt du programme
 * pour certaines exception
 */
public class PersonnalSkipPolicy implements SkipPolicy {
    @Override
    public boolean shouldSkip(Throwable throwable, int skipCount)
            throws SkipLimitExceededException {
        //Pas de sortie du programme pour ce type d'erreur
        //true : Exception non bloquante
        return (throwable instanceof IDTwoExceptionReader
                || throwable instanceof IDThreeExceptionProcessor
                || throwable instanceof IDFourExceptionWriter) ;
    }
}