package com.stef.spring.batch.jpa;

import com.stef.spring.batch.jpa.config.BatchConfig;
import com.stef.spring.batch.jpa.config.PersistenceJPAConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
@SpringBootApplication
public class BatchJpaApplication implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception{

		// Spring Java config
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(BatchConfig.class);
		context.register(PersistenceJPAConfig.class);

		context.refresh();
		runJob(context, "jobPrincipal");
	}
	private static void runJob(AnnotationConfigApplicationContext context, String batchJobName) {
		final JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		final Job job = (Job) context.getBean(batchJobName);

		log.info("Starting the batch job: {}", batchJobName);
		try {
			// To enable multiple execution of a job with the same parameters
			JobParameters jobParameters = new JobParametersBuilder().addString("jobID", String.valueOf(System.currentTimeMillis()))
					.toJobParameters();
			final JobExecution execution = jobLauncher.run(job, jobParameters);
			BatchStatus status = execution.getStatus();
			log.info("Job Status : {}", status);

		} catch (final Exception e) {
			e.printStackTrace();
			log.error("Job failed {}", e.getMessage());
		}
	}

}
