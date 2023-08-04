package com.ifms.arcondicionado.repositorios;
import com.ifms.arcondicionado.modelos.Sala;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRep extends JpaRepository<Sala, Long> {
    List<Sala> findById(Sala sala);
}
