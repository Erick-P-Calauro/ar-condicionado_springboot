package com.ifms.arcondicionado.modelos;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private char bloco;

    @NotNull
    @Size(min=3, message="O nome da sala deve ter no mínimo 3 caracteres")
    private String nome;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL)
    private List<Equipamento> equipamento_sala;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public char getBloco() {
        return bloco;
    }

    public void setBloco(char bloco) {
        this.bloco = bloco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Equipamento> getEquipamento_sala() {
        return equipamento_sala;
    }

    public void setEquipamento_sala(List<Equipamento> equipamento_sala) {
        this.equipamento_sala = equipamento_sala;
    }

}
