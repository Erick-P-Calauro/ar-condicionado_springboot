package com.ifms.arcondicionado.repositorios;
import com.ifms.arcondicionado.modelos.Modelo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRep extends JpaRepository <Modelo, Long> {
    List<Modelo> findById(Modelo modelo);
    Modelo findByNome(String nome);
}
