package com.stef.spring.batch.jpa.mapping;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;


class PaysFancaisMapperTest {

    @Test
    void toPaysFrancais() {
        PaysFancaisMapper mapper = Mappers.getMapper(PaysFancaisMapper.class);
        assertThat(mapper.toPaysFrancais(null)).isNull();
    }
}