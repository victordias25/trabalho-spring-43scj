package com.example.demo.controllerTesteUnitario;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import br.com.emissaocartao.apispring.ApiSpringApplication;
import br.com.emissaocartao.apispring.entities.Aluno;
import br.com.emissaocartao.apispring.repositories.AlunoRepository;
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

import java.util.List;


@SpringBootTest(classes = ApiSpringApplication.class)
@AutoConfigureMockMvc

public class AlunoControllerTesteUnitario {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    AlunoRepository alunoRepository;
    @Test
    public void testeRetornoSucesso_BuscarAlunoRetornaStatusCode200() throws Exception {
        var aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNomeAluno("Victor");
        aluno.setCartaoCredito("861083316026");
        Mockito.when(alunoRepository.findAll()).thenReturn(List.of(aluno));
        this.mockMvc.perform(get("/alunos"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testeSalvarSucesso_Aluno() throws Exception {
        var aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNomeAluno("Victor Dias");
        aluno.setCartaoCredito("861083316026");
        Mockito.when(alunoRepository.save(Mockito.any())).thenReturn(aluno);
        this.mockMvc.perform(post("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nomeAluno\":\"Victor Dias\",\"cartaoCredito\":\"861083316026\"}")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nomeAluno").value("Victor Dias"))
                .andExpect(jsonPath("$.cartaoCredito").value("861083316026"));
    }
}
