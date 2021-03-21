package com.stef.spring.batch.jpa.reader;

import com.stef.spring.batch.jpa.config.BatchConfig;
import com.stef.spring.batch.jpa.config.BatchTestConfiguration;
import com.stef.spring.batch.jpa.exception.IDTwoExceptionReader;
import com.stef.spring.batch.jpa.model.Pays;
import com.stef.spring.batch.jpa.repository.PaysRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(SpringRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, StepScopeTestExecutionListener.class})
@ContextConfiguration(classes = {BatchConfig.class, BatchTestConfiguration.class})
class JpaItemReaderTest {
    @InjectMocks
    @Spy
    private JpaItemReader reader;

    @Mock
    private PaysRepository respository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Test
    void read_success() throws Exception {
        List< Pays > list = new ArrayList<>();
        Pays element = new Pays();
        element.setId(0L);
        element.setCode(0);
        element.setAlphaDeux("");
        element.setAlphaTrois("");
        element.setNomEn("");
        element.setNomFr("");


        list.add(element);
        //
       doReturn(list).when( respository).findAll();
        //
        reader.before(null);
        Pays pays = reader.read();
        assertThat(pays).isNotNull();
    }
    @Test
    void read_failed() throws Exception {
        List< Pays > list = new ArrayList<>();
        Pays element = new Pays();
        element.setId(2L);
        element.setCode(0);
        element.setAlphaDeux("");
        element.setAlphaTrois("");
        element.setNomEn("");
        element.setNomFr("");


        list.add(element);
        //
        doReturn(list).when( respository).findAll();
        //
        reader.before(null);
        Assertions.assertThrows(IDTwoExceptionReader.class,()->reader.read());

    }
}