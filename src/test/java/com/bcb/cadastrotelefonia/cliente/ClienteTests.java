package com.bcb.cadastrotelefonia.cliente;

import com.bcb.cadastrotelefonia.domain.cliente.Cliente;
import com.bcb.cadastrotelefonia.domain.conta.Conta;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
public class ClienteTests {


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
        String clienteJson = "{\"nome\": \"Douglas\", \"email\": \"teste@teste.com\", \"telefone\": \"44988250406\", \"cpf\": \"0526252125\",\"cnpj\": \"0502050\", \"nomeEmp\": \"testeEMp\", \"conta\": \"PRE_PAGO\", \"saldo\": \"20\" \"limite\": \"0\"}";

        mockMvc.perform(post("/clientes")
                        .content(clienteJson).contentType(MediaType.APPLICATION_JSON))
                       .andExpect(status().isCreated());

    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
