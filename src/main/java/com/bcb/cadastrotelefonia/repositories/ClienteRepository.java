package com.bcb.cadastrotelefonia.repositories;

import com.bcb.cadastrotelefonia.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
}
