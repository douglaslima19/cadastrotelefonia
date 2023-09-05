package com.bcb.cadastrotelefonia.service;

import com.bcb.cadastrotelefonia.domain.cliente.Cliente;
import com.bcb.cadastrotelefonia.domain.conta.Conta;
import com.bcb.cadastrotelefonia.dtos.ClienteDTO;
import com.bcb.cadastrotelefonia.dtos.SmsDTO;
import com.bcb.cadastrotelefonia.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public  Cliente findByTelefone(String telefone) throws Exception{
        return this.clienteRepository.findByTelefone(telefone).orElseThrow(() -> new Exception("Cliente não encontrado"));
    }

    public Cliente findById(Long id) throws Exception{
        return this.findById(id);
    }
    public List<Cliente> getAllClientes(){
        return this.clienteRepository.findAll();
    }
    public Cliente createCliente(ClienteDTO clienteDTO){
        Cliente novoCliente = new Cliente(clienteDTO);
        this.saveCliente(novoCliente);
        return novoCliente;

    }

    public void saveCliente(Cliente cliente){
        this.clienteRepository.save(cliente);
    }

    public void validacaoClienteSMS(SmsDTO smsDTO) throws Exception {
        Cliente cliente = findByTelefone(smsDTO.telefoneSender());

        if (cliente == null){
            throw new Exception("Cliente não possui esse telefone cadastrado na nossa operadora");
        } else if(cliente.getContaTipo().equals(Conta.POS_PAGO)){
            validaLimite(cliente);
        }else if (cliente.getContaTipo().equals(Conta.PRE_PAGO)){
            validaSaldo(cliente);
        }

    }

    public void validaLimite(Cliente cliente) throws  Exception{
        if(cliente.getLimite().doubleValue() <= 0.00){
            throw new Exception("Cliente sem limite disponivel!");
        }
    }
    public void validaSaldo(Cliente cliente) throws  Exception{
        if(cliente.getSaldo().doubleValue() < 0.25){
            throw new Exception("Cliente com saldo insuficiente!");
        }
    }

    public Cliente adicionaSaldo (String telefone, BigDecimal recarga) throws Exception{
        Cliente cliente = findByTelefone(telefone);
        if (cliente.getContaTipo().equals(Conta.PRE_PAGO)) {
            cliente.setSaldo(cliente.getSaldo().add(recarga));
            this.saveCliente(cliente);
        } else  {
            throw new Exception("Cliente do tipo Pos pago");

        }
        return cliente;
    }

    public Cliente adicionaLimite (String telefone, BigDecimal limite) throws Exception{
        Cliente cliente = findByTelefone(telefone);
        if (cliente.getContaTipo().equals(Conta.POS_PAGO)) {
            cliente.setSaldo(cliente.getSaldo().add(limite));
            this.saveCliente(cliente);
        } else  {
            throw new Exception("Cliente do tipo Pre pago");

        }
        return cliente;
    }

    

}
