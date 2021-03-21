package com.stef.spring.batch.jpa.listener.reader;

import com.stef.spring.batch.jpa.model.Pays;
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
class ReaderListenerTest {
    @InjectMocks
    @Spy
    ReaderListener listener;
    @Test
    void afterRead() {
        Pays pays = new Pays();
        pays.setId(0L);
        pays.setCode(0);
        pays.setAlphaDeux("");
        pays.setAlphaTrois("");
        pays.setNomEn("");
        pays.setNomFr("");

        listener.afterRead(pays);
        verify(listener).afterRead(pays);
    }

    @Test
    void onReadError() {
        Exception exception = mock(Exception.class, RETURNS_DEFAULTS);
        listener.onReadError(exception);
        verify(listener).onReadError(exception);
    }
}