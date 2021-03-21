package com.stef.spring.batch.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pays")
public class Pays {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private Integer code;

    @Column(name = "alpha2",length = 2)
    private String alphaDeux;

    @Column(name = "alpha3",length=3)
    private String alphaTrois;

    @Column(name = "nom_en_gb",length=45)
    private String nomEn;

    @Column(name = "nom_fr_fr",length=45)
    private String nomFr;
}
