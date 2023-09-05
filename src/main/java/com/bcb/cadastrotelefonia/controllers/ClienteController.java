package com.bcb.cadastrotelefonia.controllers;

import com.bcb.cadastrotelefonia.domain.cliente.Cliente;
import com.bcb.cadastrotelefonia.dtos.ClienteDTO;
import com.bcb.cadastrotelefonia.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/clientes", produces = {"application/json"})
@Tag(name = "Clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Realiza Cadastro de novos clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    })
    @PostMapping(value = "/criaCliente", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> criaCliente(@RequestBody ClienteDTO clienteDTO) throws Exception {
        Cliente novoCliente = clienteService.createCliente(clienteDTO);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @Operation(summary = "Realiza a Consulta de saldo ")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!!"),
    })
    @GetMapping(value = "/getSaldo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BigDecimal saldo(@RequestParam String telefone) throws Exception {
        Cliente cliente = clienteService.findByTelefone(telefone);
        return cliente.getSaldo();
    }

    @Operation(summary = "Realiza a busca para os dados do cliente")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!!"),
    })
    @GetMapping(value = "/dadosCliente", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Cliente> dadosCliente(@RequestParam String telefone) throws Exception {
        Cliente cliente = clienteService.findByTelefone(telefone);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @Operation(summary = "Realiza a alteração de limite do cliente ")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!!"),
    })
    @PutMapping(value = "/alteraLimite", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> alteraLimite(@RequestBody ClienteDTO clienteDTO) throws Exception {
        Cliente cliente = clienteService.findById(clienteDTO.Id());
        if (cliente != null){
            clienteService.adicionaLimite(cliente.getTelefone(), clienteDTO.limite());
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            throw new Exception("Cliente não encontrado!!");
        }

    }

    @Operation(summary = "Realiza a alteração de saldo do cliente ")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!!"),
    })
    @PutMapping(value = "/alteraSaldo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> alteraSaldo(@RequestBody ClienteDTO clienteDTO) throws Exception {
        Cliente cliente = clienteService.findByTelefone(clienteDTO.telefone());
        if (cliente != null){
            clienteService.adicionaSaldo(cliente.getTelefone(), clienteDTO.saldo());
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            throw new Exception("Cliente não encontrado!!");
        }

    }
    @Operation(summary = "Realiza a alteração do tipo de conta do cliente")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Requisição inválida!"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos!!"),
    })
    @PutMapping(value = "/alteraConta", consumes = MediaType.APPLICATION_JSON_VALUE)
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
