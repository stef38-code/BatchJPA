package com.stef.spring.batch.jpa.listener.writer;

import com.stef.spring.batch.jpa.model.PaysFrancais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Answers.RETURNS_DEFAULTS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
class WriterListenerTest {
    @InjectMocks
    @Spy
    WriterListener listener = new WriterListener();

    private List<PaysFrancais> paysFrancaisList = new ArrayList<>();
    @BeforeEach
    void setUp() {
        PaysFrancais paysFrancais = new PaysFrancais();
        paysFrancais.setId(0L);
        paysFrancais.setNomFr("");
        paysFrancaisList.add(paysFrancais);
    }

    @Test
    void beforeWrite() {
        listener.beforeWrite(paysFrancaisList);
        verify(listener).beforeWrite(paysFrancaisList);
    }

    @Test
    void afterWrite() {
        listener.afterWrite(paysFrancaisList);
        verify(listener).afterWrite(paysFrancaisList);
    }

    @Test
    void onWriteError() {
        Exception exception = mock(Exception.class, RETURNS_DEFAULTS);
        listener.onWriteError(exception,paysFrancaisList);
        verify(listener).onWriteError(exception,paysFrancaisList);
    }
}