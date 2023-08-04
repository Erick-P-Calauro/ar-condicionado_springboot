package com.ifms.arcondicionado.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ifms.arcondicionado.modelos.Comando;

public interface ComandoRep extends JpaRepository<Comando, Long> {
}
