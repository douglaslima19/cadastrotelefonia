package com.bcb.cadastrotelefonia.dtos;

import com.bcb.cadastrotelefonia.domain.conta.Conta;

import java.math.BigDecimal;

public record ClienteDTO(Long Id, String nome, String email, String telefone, String cpf, String cnpj, String nomeEmp, Conta conta, BigDecimal saldo, BigDecimal limite) {
}
