package com.stef.spring.batch.jpa.listener.processor;

import com.stef.spring.batch.jpa.model.Pays;
import com.stef.spring.batch.jpa.model.PaysFrancais;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Answers.RETURNS_DEFAULTS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
class ProcessListenerTest {
    @InjectMocks
    @Spy
    ProcessListener listener = new ProcessListener();
    @Test
    void beforeProcess() {
        Pays pays = new Pays();
        pays.setId(0L);
        pays.setCode(0);
        pays.setAlphaDeux("");
        pays.setAlphaTrois("");
        pays.setNomEn("");
        pays.setNomFr("");


        listener.beforeProcess(pays);
        verify(listener).beforeProcess(pays);
    }

    @Test
    void afterProcess() {
        Pays pays = new Pays();
        pays.setId(0L);
        pays.setCode(0);
        pays.setAlphaDeux("");
        pays.setAlphaTrois("");
        pays.setNomEn("");
        pays.setNomFr("");

        PaysFrancais paysFrancais = new PaysFrancais();
        paysFrancais.setId(0L);
        paysFrancais.setNomFr("");

        listener.afterProcess( pays,  paysFrancais);
        verify(listener).afterProcess( pays,  paysFrancais);
    }

    @Test
    void onProcessError() {
        Pays pays = new Pays();
        pays.setId(0L);
        pays.setCode(0);
        pays.setAlphaDeux("");
        pays.setAlphaTrois("");
        pays.setNomEn("");
        pays.setNomFr("");

        Exception exception = mock(Exception.class, RETURNS_DEFAULTS);
        listener.onProcessError( pays, exception);
        verify(listener).onProcessError( pays, exception);
    }
}