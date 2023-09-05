package com.bcb.cadastrotelefonia.domain.sms;

import com.bcb.cadastrotelefonia.dtos.SmsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "sms")
@Table(name = "sms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String receiver;

    private  String sender;

    private boolean ehWhatts;

    private String mensagem;

    public Sms(SmsDTO smsDTO) {
        this.receiver = smsDTO.telefoneReceiver();
        this.sender = smsDTO.telefoneSender();
        this.ehWhatts = smsDTO.ehWhatts();
        this.mensagem = smsDTO.mensagem();
    }
}
