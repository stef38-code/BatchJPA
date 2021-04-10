package com.stef.spring.batch.jpa.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class PaysTest {
    @Test
    void allArguments(){
        String nomFr = "Afghanistan";
        String nomEn = "Afghanistan";
        String alphaTrois = "AFG";
        String alphaDeux = "AF";
        int code = 4;
        long id = 1L;
        Pays pays = new Pays(id, code, alphaDeux, alphaTrois, nomEn, nomFr);
        assertThat(pays.getId()).isEqualTo(id);
        assertThat(pays.getCode()).isEqualTo(code);
        assertThat(pays.getAlphaDeux()).isEqualTo(alphaDeux);
        assertThat(pays.getAlphaTrois()).isEqualTo(alphaTrois);
        assertThat(pays.getNomEn()).isEqualTo(nomEn);
        assertThat(pays.getNomFr()).isEqualTo(nomFr);
    }
}
