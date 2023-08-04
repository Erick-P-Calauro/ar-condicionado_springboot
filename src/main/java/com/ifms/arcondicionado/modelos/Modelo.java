package com.ifms.arcondicionado.modelos;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL)
    private List<Equipamento> equipamento_modelo;

    @OneToOne(mappedBy = "modelo")
    private Comando comando;

    public Long getId() {
        return id;
    }

    public Comando getComando() {
        return comando;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Equipamento> getEquipamento_modelo() {
        return equipamento_modelo;
    }

    public void setEquipamento_modelo(List<Equipamento> equipamento_modelo) {
        this.equipamento_modelo = equipamento_modelo;
    }

    public void setComando(Comando comando) {
        this.comando = comando;
    }

}
