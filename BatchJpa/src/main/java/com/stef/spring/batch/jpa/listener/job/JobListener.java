package com.stef.spring.batch.jpa.listener.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("--------------- Debug du job ---------------");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        Collection<StepExecution> listeStep = jobExecution.getStepExecutions();
        listeStep.forEach(stepExecution ->{

            log.info("----------------------- Debut Bilan {} --------------------------",stepExecution.getStepName());
            log.info("le nombre actuel d'éléments lus pour cette exécution: {}",stepExecution.getReadCount());
            log.info("le nombre actuel d'éléments écrits pour cette exécution: {}",stepExecution.getWriteCount());
            log.info("le nombre total d'éléments ignorés: {}",stepExecution.getSkipCount());
            log.info("le nombre d'enregistrements ignorés lors de la lecture: {}",stepExecution.getReadSkipCount());
            log.info("le nombre d'enregistrements ignorés lors de l'ecriture': {}",stepExecution.getWriteSkipCount());
            log.info("le nombre d'enregistrements ignorés lors du traitement: {}",stepExecution.getProcessSkipCount());
            //

            List<Throwable> listException = stepExecution.getFailureExceptions();
            listException.forEach(element->log.info("Message erreur: {}",element.getMessage()));
            //
            infoTempsStep(stepExecution);
            //
            log.info("l'état actuel de cette étape: {}",stepExecution.getStatus());
            log.info("----------------------- Fin Bilan {} --------------------------",stepExecution.getStepName());
        });
        log.info("--------------- Fin du job ---------------");
        log.info("Job status: " + jobExecution.getStatus());

    }

    private void infoTempsStep(StepExecution stepExecution){
        Date dateDebut = stepExecution.getStartTime();
        Date dateFin = stepExecution.getEndTime();
        long diff = dateFin.getTime() - dateDebut.getTime();
        log.info("Temps de traitement debut:{} fin:{} => {} minutes ",dateDebut,dateFin, TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS));
    }
}
