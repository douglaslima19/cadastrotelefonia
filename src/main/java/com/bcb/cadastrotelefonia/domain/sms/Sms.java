package com.bcb.cadastrotelefonia.domain.sms;

import com.bcb.cadastrotelefonia.dtos.SmsDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "sms")
@Table(name = "sms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String telefoneReceiver;

    private  String telefoneSender;

    private boolean ehWhatts;

    private String mensagem;

    public Sms(SmsDTO smsDTO) {
        this.telefoneReceiver = smsDTO.telefoneReceiver();
        this.telefoneSender = smsDTO.telefoneSender();
        this.ehWhatts = smsDTO.ehWhatts();
        this.mensagem = smsDTO.mensagem();
    }
}
