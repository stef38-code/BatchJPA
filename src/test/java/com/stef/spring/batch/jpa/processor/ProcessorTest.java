package com.stef.spring.batch.jpa.processor;

import com.stef.spring.batch.jpa.config.BatchConfig;
import com.stef.spring.batch.jpa.config.BatchTestConfiguration;
import com.stef.spring.batch.jpa.exception.IDThreeExceptionProcessor;
import com.stef.spring.batch.jpa.model.Pays;
import com.stef.spring.batch.jpa.model.PaysFrancais;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, StepScopeTestExecutionListener.class})
@ContextConfiguration(classes = {BatchConfig.class, BatchTestConfiguration.class})
class ProcessorTest {
    @Autowired
    private Processor processor;

     @Test
    void testProcess_ok() throws Exception {
        Pays element = new Pays();
        element.setId(0L);
        element.setCode(0);
        element.setAlphaDeux("");
        element.setAlphaTrois("");
        element.setNomEn("");
        element.setNomFr("kHLUcfST");


        PaysFrancais elementTraite = processor.process(element);

        // AssertJ
        assertThat(elementTraite).isNotNull();
        assertThat(elementTraite.getNomFr()).isEqualTo(element.getNomFr());
    }
    @Test
    void testProcess_skip() throws Exception {
        Pays element = new Pays();
        element.setId(3L);
        element.setCode(0);
        element.setAlphaDeux("");
        element.setAlphaTrois("");
        element.setNomEn("");
        element.setNomFr("");
        Assertions.assertThrows(IDThreeExceptionProcessor.class,()->processor.process(element));
    }
}