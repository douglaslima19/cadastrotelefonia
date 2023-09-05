package com.bcb.cadastrotelefonia.domain.cliente;

import com.bcb.cadastrotelefonia.domain.conta.Conta;
import com.bcb.cadastrotelefonia.dtos.ClienteDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "cliente")
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    @Column(unique = true)
    private String cpf;

    private String cnpj;

    private String nomeEmp;

    private Conta contaTipo;

    private BigDecimal saldo;

    private  BigDecimal limite;

    public Cliente(ClienteDTO clienteDTO) {
        this.nome = clienteDTO.nome();
        this.email = clienteDTO.email();
        this.telefone = clienteDTO.telefone();
        this.cpf = clienteDTO.cpf();
        this.cnpj = clienteDTO.cnpj();
        this.nomeEmp = clienteDTO.nomeEmp();
        this.contaTipo = clienteDTO.conta();
        this.saldo = clienteDTO.saldo();
        this.limite = clienteDTO.limite();
    }

}
