package com.stef.spring.batch.jpa.listener.step;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Answers.RETURNS_DEFAULTS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
class StepListenerTest {
    @Mock
    private StepExecution stepExecution;;


    @Test
    void afterStep() {
        StepListener stepListener = new StepListener();
        stepExecution = mock(StepExecution.class, RETURNS_DEFAULTS);
        doReturn(2).when(stepExecution).getSkipCount();
        ExitStatus actual = stepListener.afterStep(stepExecution);
        assertThat(actual).isEqualTo(ExitStatus.FAILED);
    }
}