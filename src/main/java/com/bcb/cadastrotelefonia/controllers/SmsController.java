package com.bcb.cadastrotelefonia.controllers;

import com.bcb.cadastrotelefonia.domain.sms.Sms;
import com.bcb.cadastrotelefonia.dtos.SmsDTO;
import com.bcb.cadastrotelefonia.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping(value = "/sms", produces = {"applications/json"})
public class SmsController {

    @Autowired
    private SmsService  smsService;

    @Operation(summary = "Realiza envio de SMS", method = "POST")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "201", description = "Transação realizada com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!!"),
    })
    @PostMapping(value = "/enviaSMS", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sms> enviaSms(@RequestBody SmsDTO smsDTO) throws Exception{
        Sms sms = smsService.enviarSMS(smsDTO);
        return new ResponseEntity<>(sms, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca SMS por telefone de envio", method = "GET")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "Transação realizada com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!!"),
    })
    @GetMapping(value = "/getSmsBySender", produces = MediaType.ALL_VALUE)
    public List<Sms> getSmsBySender(@RequestParam String telefoneSender) throws Exception{
        List<Sms> sms = smsService.findBySender(telefoneSender);
        return sms;
    }
    @Operation(summary = "Busca SMS por telefone de recebimento", method = "GET")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "Transação realizada com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!!"),
    })
    @GetMapping(value = "/getSmsByReceiver", produces = MediaType.ALL_VALUE)
    public List<Sms> getSmsByReceiver(@RequestParam String telefoneReceiver) throws Exception{
        List<Sms> sms = smsService.findByReceiver(telefoneReceiver);
        return sms;
    }
}
