package com.stef.spring.batch.jpa.mapping;

import com.stef.spring.batch.jpa.model.Pays;
import com.stef.spring.batch.jpa.model.PaysFrancais;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaysFancaisMapper {
    PaysFrancais toPaysFrancais(Pays pays);
}
