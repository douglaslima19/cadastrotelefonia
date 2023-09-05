package com.bcb.cadastrotelefonia.service;

import com.bcb.cadastrotelefonia.domain.sms.Sms;
import com.bcb.cadastrotelefonia.dtos.SmsDTO;
import com.bcb.cadastrotelefonia.repositories.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmsService {

    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private ClienteService clienteService;

    public Sms enviarSMS(SmsDTO smsDTO) throws Exception{
        clienteService.validacaoClienteSMS(smsDTO);
        Sms enviaSMS = new Sms(smsDTO);
        smsRepository.save(enviaSMS);
        return enviaSMS;
    }

    public List<Sms> findBySender(String telefoneSender) throws Exception{
        return this.smsRepository.findBySender(telefoneSender);

    }
    public List<Sms> findByReceiver(String telefoneReceiver){
        return this.smsRepository.findByReceiver(telefoneReceiver);

    }

}
