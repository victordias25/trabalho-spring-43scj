package com.example.demo.controllerTesteIntegracao;

import br.com.emissaocartao.apispring.controllers.AlunoController;
import br.com.emissaocartao.apispring.entities.Aluno;
import com.example.demo.DemoApplicationTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class AlunoControllerTesteIntegracao extends DemoApplicationTests {
    private MockMvc mockMvc;
    @Autowired
    private AlunoController alunoController;
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(alunoController).build();
    }

    @Test
    public void criarUsuarioComDadosCorretos_RetornarStatusCode200() throws Exception {
        var aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNomeAluno("Victor Dias");
        aluno.setCartaoCredito("861083316026");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(aluno);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void criarUsuarioComDadosIncorretos_RetornarStatusCode400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
