package com.stef.spring.batch.jpa.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaysFrancaisTest {
@Test
    void allArgument(){
    long id = 769L;
    String nomFr = "tidy";
    PaysFrancais paysFrancais = new PaysFrancais(id, nomFr);
    assertThat(paysFrancais.getId()).isEqualTo(id);
    assertThat(paysFrancais.getNomFr()).isEqualTo(nomFr);
}
}