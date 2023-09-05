package com.bcb.cadastrotelefonia.controllers;

import com.bcb.cadastrotelefonia.domain.cliente.Cliente;
import com.bcb.cadastrotelefonia.dtos.ClienteDTO;
import com.bcb.cadastrotelefonia.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> criaCliente(@RequestBody ClienteDTO clienteDTO) throws Exception {
        Cliente novoCliente = clienteService.createCliente(clienteDTO);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }
    @GetMapping("/getSaldo")
    public BigDecimal saldo(@RequestParam String telefone) throws Exception {
        Cliente cliente = clienteService.findByTelefone(telefone);
        return cliente.getSaldo();
    }

    @GetMapping("/dadosCliente")
    public ResponseEntity<Cliente> dadosCliente(@RequestParam String telefone) throws Exception {
        Cliente cliente = clienteService.findByTelefone(telefone);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/alteraLimite")
    public ResponseEntity<Cliente> alteraLimite(@RequestBody ClienteDTO clienteDTO) throws Exception {
        Cliente cliente = clienteService.findById(clienteDTO.Id());
        if (cliente != null){
            clienteService.adicionaLimite(cliente.getTelefone(), clienteDTO.limite());
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            throw new Exception("Cliente não encontrado!!");
        }

    }
    @PutMapping("/alteraSaldo")
    public ResponseEntity<Cliente> alteraSaldo(@RequestBody ClienteDTO clienteDTO) throws Exception {
        Cliente cliente = clienteService.findByTelefone(clienteDTO.telefone());
        if (cliente != null){
            clienteService.adicionaSaldo(cliente.getTelefone(), clienteDTO.saldo());
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            throw new Exception("Cliente não encontrado!!");
        }

    }
    @PutMapping("/alteraConta")
    public ResponseEntity<Cliente> alteraConta(@RequestBody ClienteDTO clienteDTO) throws Exception {
        Cliente cliente = clienteService.findByTelefone(clienteDTO.telefone());
        if (cliente != null){
            clienteService.alteraConta(clienteDTO);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            throw new Exception("Cliente não encontrado!!");
        }

    }

}
