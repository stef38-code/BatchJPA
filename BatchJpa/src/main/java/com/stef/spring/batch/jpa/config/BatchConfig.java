package com.stef.spring.batch.jpa.config;

import com.stef.spring.batch.jpa.exception.IDFourExceptionWriter;
import com.stef.spring.batch.jpa.exception.IDThreeExceptionProcessor;
import com.stef.spring.batch.jpa.exception.IDTwoExceptionReader;
import com.stef.spring.batch.jpa.listener.job.JobListener;
import com.stef.spring.batch.jpa.listener.processor.ProcessListener;
import com.stef.spring.batch.jpa.listener.reader.ReaderListener;
import com.stef.spring.batch.jpa.listener.step.StepListener;
import com.stef.spring.batch.jpa.listener.writer.WriterListener;
import com.stef.spring.batch.jpa.model.Pays;
import com.stef.spring.batch.jpa.model.PaysFrancais;
import com.stef.spring.batch.jpa.policy.PersonnalSkipPolicy;
import com.stef.spring.batch.jpa.processor.Processor;
import com.stef.spring.batch.jpa.reader.JpaItemReader;
import com.stef.spring.batch.jpa.writer.JpaItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableBatchProcessing
@ComponentScan("com.stef.spring.batch.jpa.*")
@EntityScan({"com.stef.spring.batch.jpa.model"})
@EnableJpaRepositories("com.stef.spring.batch.jpa.repository")
public class BatchConfig extends DefaultBatchConfigurer {
    /**
     * Definition du nombre d'elements traite à chaque fois
     */
    @Value("${spring.job.chunk}")
    private int chunkSize;
    /**
     * Nom du job
     */
    private static final String JOB_NAME = "listStudentsJob";
    private static final String STEP_NAME = "step1";
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    /*******************************************
     * Liste des beans
     ********************************************/
    @Bean
    public JpaItemReader reader() {
        return new JpaItemReader();
    }

    @Bean
    public Processor processor() {
        return new Processor();
    }

    @Bean
    public JpaItemWriter writer() {
        return new JpaItemWriter();
    }
    @Bean
    public StepListener stepExecutionListener() {
        return new StepListener();
    }

    @Bean
    public JobListener jobExecutionListener() {
        return new JobListener();
    }

    @Bean
    public ReaderListener readerListener() {
        return new ReaderListener();
    }

    @Bean
    public ProcessListener processListener() {
        return new ProcessListener();
    }

    @Bean
    public WriterListener writerListener() {
        return new WriterListener();
    }
    /**
     *
     * @return
     */
    @Bean
    public Step studentStep(JpaItemReader reader,
                            JpaItemWriter writer,
                            Processor processor,
                            StepListener stepListener,
                            ReaderListener readerListener,
                            ProcessListener processListener,
                            WriterListener writerListener
    ) {
        return stepBuilderFactory.get(STEP_NAME)
                .< Pays, PaysFrancais >chunk(chunkSize)
                //gestion des erreurs a eviter -> le programme continu
                .faultTolerant()
                //Definition d'une police pour gerer
                // les exception pendant le traitement
                .skipPolicy(new PersonnalSkipPolicy())
                //Les exceptions qui vont être surveillées
                .skip(IDTwoExceptionReader.class)
                .skip(IDThreeExceptionProcessor.class)
                .skip(IDFourExceptionWriter.class)
                //Lecture des données en db avec son listener
                .reader(reader).listener(readerListener)
                //transformation des données avec son listener
                .processor(processor).listener(processListener)
                //Ecriture des données transformées en db avec son listener
                .writer(writer).listener(writerListener)
                //listener du step
                .listener(stepListener)
                .build();
    }
    @Bean
    @Qualifier("JobA")
    public Job listStudentsJob(Step step, JobListener jobListener) {
        return jobBuilderFactory.get(JOB_NAME)
                .listener(jobListener)
                .flow(step)
                .end()
                .build();
    }
    /*
   Despite trying to create an in-memory JobRepository, the context will not
   load if it finds more than one DataSource bean. If the intent is to use an
   in-memory repository, the absence or presence of a DataSource should be
   irrelevant.
    */
    @Override
    protected JobRepository createJobRepository() throws Exception {
        //Pour ne pas avoir à céer les tables de batch en DB
        MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(new ResourcelessTransactionManager());
        return factory.getObject();
    }
}
