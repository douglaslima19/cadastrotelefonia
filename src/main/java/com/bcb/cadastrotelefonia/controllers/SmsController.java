package com.bcb.cadastrotelefonia.controllers;

import com.bcb.cadastrotelefonia.domain.sms.Sms;
import com.bcb.cadastrotelefonia.dtos.SmsDTO;
import com.bcb.cadastrotelefonia.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/sms")
public class SmsController {

    @Autowired
    private SmsService  smsService;

    @PostMapping("/enviaSMS")
    public ResponseEntity<Sms> enviaSms(@RequestBody SmsDTO smsDTO) throws Exception{
        Sms sms = smsService.enviarSMS(smsDTO);
        return new ResponseEntity<>(sms, HttpStatus.CREATED);
    }

    @GetMapping("/getSmsBySender")
    public List<Sms> getSmsBySender(@RequestParam String telefoneSender) throws Exception{
        List<Sms> sms = smsService.findBySender(telefoneSender);
        return sms;
    }

    @GetMapping("/getSmsByReceiver")
    public List<Sms> getSmsByReceiver(@RequestParam String telefoneReceiver) throws Exception{
        List<Sms> sms = smsService.findByReceiver(telefoneReceiver);
        return sms;
    }
}
