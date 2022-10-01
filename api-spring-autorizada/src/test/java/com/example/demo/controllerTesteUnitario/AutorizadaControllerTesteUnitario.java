package com.example.demo.controllerTesteUnitario;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import br.com.autorizada.apispring.ApiSpringApplication;
import br.com.autorizada.apispring.entities.Autorizada;
import br.com.autorizada.apispring.repositories.AutorizadaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.List;


@SpringBootTest(classes = ApiSpringApplication.class)
@AutoConfigureMockMvc

public class AutorizadaControllerTesteUnitario {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    AutorizadaRepository autorizadaRepository;

    @Test
    public void testeRetornoSucesso_BuscarTransacao() throws Exception {
        var autorizada = new Autorizada();
        autorizada.setId(1L);
        autorizada.setCartaoCredito("861083316026");
        autorizada.setValorTotal(BigDecimal.valueOf(100.00));
        Mockito.when(autorizadaRepository.findAll()).thenReturn(List.of(autorizada));
        this.mockMvc.perform(get("/autorizada")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testeSalvarSucesso_Transacao() throws Exception {
        var autorizada = new Autorizada();
        autorizada.setId(1L);
        autorizada.setCartaoCredito("861083316026");
        autorizada.setValorTotal(BigDecimal.valueOf(100));
        Mockito.when(autorizadaRepository.save(Mockito.any())).thenReturn(autorizada);
        this.mockMvc.perform(post("/autorizada")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"cartaoCredito\"" + ":\"861083316026\",\"valorTotal\":\"100\"}")
                        .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(1L))
                .andExpect(jsonPath("$.cartaoCredito")
                        .value("861083316026"))
                .andExpect(jsonPath("$.valorTotal").value("100"));
    }
}
