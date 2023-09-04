package com.bcb.cadastrotelefonia.domain.cliente;

import com.bcb.cadastrotelefonia.domain.conta.Conta;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "cliente")
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private int telefone;

    @Column(unique = true)
    private String cpf;

    private String cnpj;

    private String nomeEmp;

    private Conta contaTipo;

}
