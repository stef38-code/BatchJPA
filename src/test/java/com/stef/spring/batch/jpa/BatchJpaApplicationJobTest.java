package com.stef.spring.batch.jpa;

import com.stef.spring.batch.jpa.config.BatchConfig;
import com.stef.spring.batch.jpa.config.BatchTestConfiguration;
import com.stef.spring.batch.jpa.config.PersistenceJPAConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@SpringBatchTest
@ExtendWith({SpringExtension.class})
@EnableAutoConfiguration
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ContextConfiguration(classes = {BatchConfig.class, BatchTestConfiguration.class,
        //ajouter ce fichier de conf afin de permettre l'ajout de données en base
        PersistenceJPAConfig.class})
@Slf4j
class BatchJpaApplicationJobTest {
    @Autowired
    @Qualifier("jobPrincipal")
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobRepository jobRepository;

    private JobLauncherTestUtils jobLauncherTestUtils ;

    @BeforeEach
    void setUp() {
        jobLauncherTestUtils = getJobLauncherTestUtils(job);
    }

    @Test
     void tesLaunchJob_Completed() throws Exception {

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        JobInstance actualJobInstance = jobExecution.getJobInstance();
        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();
//
        assertThat(actualJobInstance.getJobName(), is("jobPrincipal"));
        assertThat(actualJobExitStatus.getExitCode(), is("COMPLETED"));
    }
    @Test
    @Disabled
     void tesLaunchJob_Failed() throws Exception {

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        JobInstance actualJobInstance = jobExecution.getJobInstance();
        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();
//
        assertThat(actualJobInstance.getJobName(), is("jobPrincipal"));
        assertThat(actualJobExitStatus.getExitCode(), is("FAILED"));
    }
    private JobLauncherTestUtils getJobLauncherTestUtils(Job job) {
        JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
        jobLauncherTestUtils.setJobLauncher(jobLauncher);
        jobLauncherTestUtils.setJobRepository(jobRepository);
        jobLauncherTestUtils.setJob(job);
        return jobLauncherTestUtils;
    }

}