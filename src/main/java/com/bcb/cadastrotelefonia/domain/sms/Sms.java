package com.bcb.cadastrotelefonia.domain.sms;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "sms")
@Table(name = "sms")
@Data
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String telefone;

    private  String telEnvio;

    private boolean ehWhatts;

    private String mensagem;
}
