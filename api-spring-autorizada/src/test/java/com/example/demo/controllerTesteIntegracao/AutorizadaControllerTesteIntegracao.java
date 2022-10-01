package com.example.demo.controllerTesteIntegracao;
import br.com.autorizada.apispring.controllers.AtorizadaController;
import br.com.autorizada.apispring.entities.Autorizada;
import com.example.demo.DemoApplicationTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class AutorizadaControllerTesteIntegracao extends DemoApplicationTests {
    private MockMvc mockMvc;
    @Autowired
    private AtorizadaController atorizadaController;
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(atorizadaController).build();
    }

    @Test
    public void criarUsuarioComDadosCorretos_RetornarStatusCode200() throws Exception {
        var autorizada = new Autorizada();
        autorizada.setId(1L);
        autorizada.setCartaoCredito("861083316026");
        autorizada.setValorTotal(BigDecimal.valueOf(100.00));
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(autorizada);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/autorizada")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void criarUsuarioComDadosIncorretos_RetornarStatusCode400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/autorizada")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
