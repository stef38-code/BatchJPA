package com.stef.spring.batch.jpa;

import com.stef.spring.batch.jpa.config.BatchConfig;
import com.stef.spring.batch.jpa.config.BatchTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;

@SpringBatchTest()
@EnableAutoConfiguration
@ContextConfiguration(classes = {BatchConfig.class, BatchTestConfiguration.class})
class BatchJpaApplicationTest {

    @Test
    void run() throws Exception {
        BatchJpaApplication batchJpaApplication = new BatchJpaApplication();
        batchJpaApplication.run(null);
    }
}