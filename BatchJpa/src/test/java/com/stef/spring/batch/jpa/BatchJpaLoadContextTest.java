package com.stef.spring.batch.jpa;

import com.stef.spring.batch.jpa.config.BatchConfig;
import com.stef.spring.batch.jpa.config.BatchTestConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@RunWith(SpringRunner.class)
@SpringBatchTest
@EnableAutoConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ContextConfiguration(classes = {BatchConfig.class, BatchTestConfiguration.class})
class BatchJpaLoadContextTest {

        @Test
         void tesContextLoads() {
        }

}