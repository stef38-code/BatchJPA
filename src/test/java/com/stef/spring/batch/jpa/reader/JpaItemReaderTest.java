package com.stef.spring.batch.jpa.reader;

import com.stef.spring.batch.jpa.config.BatchConfig;
import com.stef.spring.batch.jpa.config.BatchTestConfiguration;
import com.stef.spring.batch.jpa.exception.IDTwoExceptionReader;
import com.stef.spring.batch.jpa.model.Pays;
import com.stef.spring.batch.jpa.repository.PaysRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, StepScopeTestExecutionListener.class})
@ContextConfiguration(classes = {BatchConfig.class, BatchTestConfiguration.class})
class JpaItemReaderTest {
    @InjectMocks
    private JpaItemReader reader;
    @Mock
    private PaysRepository repository;

    @Test
    void read_success() throws Exception {
        List< Pays > list = new ArrayList<>();
        Pays element = new Pays();
        element.setId(0L);
        element.setCode(0);
        element.setAlphaDeux("15lw29");
        element.setAlphaTrois("2k3YYr7n");
        element.setNomEn("jN93Qvp4");
        element.setNomFr("891vp");


        list.add(element);
        //
        doReturn(list).when(repository).findAll();
       //when( repository.findAll()).thenReturn(list);
        //permet d'initialiser l'iterator
        //doReturn(element).when(reader).before(any());
        Pays pays = reader.read();
        assertThat(pays).isNotNull();
        assertThat(pays.getId()).isEqualTo(element.getId());
        assertThat(pays.getCode()).isEqualTo(element.getCode());
        assertThat(pays.getAlphaDeux()).isEqualTo(element.getAlphaDeux());
        assertThat(pays.getAlphaTrois()).isEqualTo(element.getAlphaTrois());
        assertThat(pays.getNomEn()).isEqualTo(element.getNomEn());
        assertThat(pays.getNomFr()).isEqualTo(element.getNomFr());
    }
    @Test
    void read_success_emptyList() throws Exception {
        List< Pays > list = Collections.emptyList();
       //doReturn(list).when( respository).findAll();
        //permet d'initialiser l'iterator
        //reader.before(null);
        Pays pays = reader.read();
        assertThat(pays).isNull();
    }
    @Test
    void read_success_nullList() throws Exception {
        //doReturn(null).when( respository).findAll();
        //permet d'initialiser l'iterator
        reader.before(null);
        Pays pays = reader.read();
        //Assertions.assertThrows( (pays).isNull();
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
        //doReturn(list).when( respository).findAll();
        //
        reader.before(null);
        Assertions.assertThrows(IDTwoExceptionReader.class,()->reader.read());

    }
}