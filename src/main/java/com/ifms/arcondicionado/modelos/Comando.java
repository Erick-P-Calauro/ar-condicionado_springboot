package com.ifms.arcondicionado.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Comando {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    String L20;

    @NotNull
    String L21;

    @NotNull
    String L22;

    @NotNull
    String L23;

    @NotNull
    String L24;

    @NotNull
    String L25;

    @NotNull
    String OFF;

    @OneToOne
    private Modelo modelo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getL20() {
        return L20;
    }

    public void setL20(String l20) {
        L20 = l20;
    }

    public String getL21() {
        return L21;
    }

    public void setL21(String l21) {
        L21 = l21;
    }

    public String getL22() {
        return L22;
    }

    public void setL22(String l22) {
        L22 = l22;
    }

    public String getL23() {
        return L23;
    }

    public void setL23(String l23) {
        L23 = l23;
    }

    public String getL24() {
        return L24;
    }

    public void setL24(String l24) {
        L24 = l24;
    }

    public String getL25() {
        return L25;
    }

    public void setL25(String l25) {
        L25 = l25;
    }

    public String getOFF() {
        return OFF;
    }

    public void setOFF(String OFF) {
        this.OFF = OFF;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
