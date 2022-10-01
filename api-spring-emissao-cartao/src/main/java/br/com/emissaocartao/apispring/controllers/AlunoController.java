package br.com.emissaocartao.apispring.controllers;

import br.com.emissaocartao.apispring.entities.Aluno;
import br.com.emissaocartao.apispring.repositories.AlunoRepository;
import br.com.emissaocartao.apispring.services.CartaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepository;

    public ResponseEntity<Aluno> consultarCartaoCadastado(Aluno aluno){
        Optional<Aluno> alunoCartaoCredito = alunoRepository.findByCartaoCredito(aluno.getCartaoCredito());
        if (alunoCartaoCredito.isPresent()) {
            throw new CartaoException("Cartão de Crédito não encontrado");
        }
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Aluno cadastrar(@RequestBody Aluno aluno) throws CartaoException {
        this.consultarCartaoCadastado(aluno);
        return alunoRepository.save(aluno);
    }
    @GetMapping
    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno buscarPorId(@PathVariable Long id) {
        var alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return alunoOptional.get();
    }

    @GetMapping(path = "/find")
    public ResponseEntity<Optional<Aluno>> finbyCartaoCredito(@RequestParam String cartaoCredito) {
        return ResponseEntity.ok(alunoRepository.findByCartaoCredito(cartaoCredito));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable Long id) {
        var alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        alunoRepository.delete(alunoOptional.get());
    }

    @PutMapping("/{id}")
    public Aluno atualizarPorId(@PathVariable Long id, @RequestBody Aluno aluno) {
        var alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        aluno.setId(id);
        return alunoRepository.save(aluno);
    }
}