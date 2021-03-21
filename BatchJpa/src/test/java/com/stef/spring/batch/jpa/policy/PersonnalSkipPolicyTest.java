package com.stef.spring.batch.jpa.policy;

import com.stef.spring.batch.jpa.exception.IDFourExceptionWriter;
import com.stef.spring.batch.jpa.exception.IDThreeExceptionProcessor;
import com.stef.spring.batch.jpa.exception.IDTwoExceptionReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PersonnalSkipPolicyTest {
    private PersonnalSkipPolicy personnalSkipPolicy;

    @BeforeEach
    void setUp() {
        personnalSkipPolicy = new PersonnalSkipPolicy();
    }

    @Test
    void shouldSkip_ExceptionParam() {
        Exception exception = new Exception("test");
        boolean value = personnalSkipPolicy.shouldSkip(exception, 0);
        assertThat(value).isFalse();
    }
    private static Stream< Throwable > getListException() {
        return  Stream.of(new IDTwoExceptionReader(),new IDThreeExceptionProcessor(),
                new IDFourExceptionWriter());
    }
    @ParameterizedTest(name = "{index} ==> Class ''{0}''")
    @MethodSource("getListException")
    @DisplayName("Test les exceptions skip")
    void shouldSkip(Exception classValue) {
        assertThat(personnalSkipPolicy.shouldSkip(classValue, 0)).isTrue();
    }
}