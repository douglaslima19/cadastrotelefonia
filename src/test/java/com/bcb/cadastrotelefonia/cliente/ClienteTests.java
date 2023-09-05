package com.bcb.cadastrotelefonia.cliente;

import com.bcb.cadastrotelefonia.domain.cliente.Cliente;
import com.bcb.cadastrotelefonia.domain.conta.Conta;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ClienteTests {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void tstCriarCliente() throws Exception{
        Cliente cliente = Cliente.builder()
                .nome("Douglas")
                .email("teste@teste.com")
                .telefone("44988250406")
                .cpf("0526252125")
                .cnpj("0502050")
                .nomeEmp("testeEMp")
                .contaTipo(Conta.PRE_PAGO)
                .saldo(BigDecimal.valueOf(20))
                .limite(BigDecimal.valueOf(0))
                .build();

        mockMvc.perform(post("/clientes/criaCliente").contentType(MediaType.APPLICATION_JSON).content(asJsonString(cliente))).andExpect(status().is2xxSuccessful());

    }

    @Test
    public  void tstConsultaSaldo2() throws Exception{
        BigDecimal expectedValue = BigDecimal.valueOf(20.00).setScale(1, RoundingMode.HALF_UP);
        BigDecimal actualValue = new BigDecimal("20.00").setScale(1, RoundingMode.HALF_UP);

        mockMvc.perform(get("/clientes/getSaldo").param("telefone","44988250406").contentType(MediaType.ALL)).andExpect(content().string("20.00"));
    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}