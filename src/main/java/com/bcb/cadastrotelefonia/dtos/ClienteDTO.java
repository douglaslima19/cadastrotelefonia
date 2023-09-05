package com.bcb.cadastrotelefonia.dtos;

import com.bcb.cadastrotelefonia.domain.conta.Conta;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ClienteDTO(Long Id, String nome, String email, String telefone, String cpf, String cnpj, String nomeEmp, Conta conta, BigDecimal saldo, BigDecimal limite) {
}
