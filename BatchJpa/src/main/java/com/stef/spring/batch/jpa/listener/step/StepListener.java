package com.stef.spring.batch.jpa.listener.step;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class StepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("beforeStep....");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        //changement du status si il y a des skips
        if (conditionalCheck(stepExecution)) {
            return ExitStatus.FAILED;
        }
        return  stepExecution.getExitStatus();
    }

    /**
     * Il y a eu des skip donc retour avec failed
     * @param stepExecution
     * @return
     */
    private boolean conditionalCheck(StepExecution stepExecution) {
        if(stepExecution.getSkipCount() != 0 ){
            log.info("Des elements skpippes....");
            return true;
        }
        return false;
    }

}
