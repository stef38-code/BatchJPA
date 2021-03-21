package com.stef.spring.batch.jpa.all;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.pojo.tester.api.assertion.Method;
import tooltest.ClassesForPackage;

import java.util.stream.Stream;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

@Slf4j
 class ClasseEntitiesDtoTest {
    private static Stream< Class > getListClass() throws ClassNotFoundException {
        return ClassesForPackage.getListClass("com.stef.spring.batch.jpa", true);
    }

    private static Stream< Class > getListEntitiesClass() throws ClassNotFoundException {
        return ClassesForPackage.getAllEntities("com.stef.spring.batch.jpa", true);
    }

    private static Stream< Class > getListDataAnnotationClass() throws ClassNotFoundException {
        return ClassesForPackage.getAllDataAnnotation("com.stef.spring.batch.jpa", true);
    }

    @ParameterizedTest(name = "{index} ==> Class ''{0}''")
    @MethodSource("getListClass")
    @DisplayName("Test les Constructeurs")
    void testConstructors(Class classValue) {
      assertPojoMethodsFor(classValue).testing(Method.CONSTRUCTOR).areWellImplemented();
    }

    @ParameterizedTest(name = "{index} ==> Class ''{0}''")
    @MethodSource({"getListEntitiesClass"})
    @DisplayName("Test les Annotations Entity")
    void testAnnotationEntity(Class classValue) {
        assertPojoMethodsFor(classValue)
                .testing(Method.GETTER)
                .testing(Method.SETTER)
                .areWellImplemented();
    }
}
