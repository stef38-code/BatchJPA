package com.stef.spring.batch.jpa.writer;

import com.stef.spring.batch.jpa.config.BatchConfig;
import com.stef.spring.batch.jpa.config.BatchTestConfiguration;
import com.stef.spring.batch.jpa.exception.IDFourExceptionWriter;
import com.stef.spring.batch.jpa.model.PaysFrancais;
import com.stef.spring.batch.jpa.repository.PaysFrancaisRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, StepScopeTestExecutionListener.class})
@ContextConfiguration(classes = {BatchConfig.class, BatchTestConfiguration.class})
class JpaItemWriterTest {

    @InjectMocks
    @Spy
    private JpaItemWriter writer;

    @Mock
    private PaysFrancaisRepository respository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
     void write_Success() throws Exception {
        List< PaysFrancais > list = new ArrayList<>();
        PaysFrancais element = new PaysFrancais();
        element.setId(0L);
        element.setNomFr("");


        list.add(element);
        //
        when(respository.save(any())).thenReturn( new ArrayList<>());
        //
        writer.write(list);
        verify(writer).write(list);
    }
    @Test
     void write_failed() throws Exception {
        List< PaysFrancais > list = new ArrayList<>();
        PaysFrancais element = new PaysFrancais();
        element.setId(4L);
        element.setNomFr("");
        list.add(element);
        //
        when(respository.save(any())).thenReturn( new ArrayList<>());
        //
        Assertions.assertThrows(IDFourExceptionWriter.class,()-> writer.write(list));
    }

}