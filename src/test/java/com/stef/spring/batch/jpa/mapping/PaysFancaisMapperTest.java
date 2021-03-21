package com.stef.spring.batch.jpa.mapping;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
class PaysFancaisMapperTest {

    @Test
    void toPaysFrancais() {
        PaysFancaisMapper mapper = Mappers.getMapper(PaysFancaisMapper.class);
        assertThat(mapper.toPaysFrancais(null)).isNull();
    }
}