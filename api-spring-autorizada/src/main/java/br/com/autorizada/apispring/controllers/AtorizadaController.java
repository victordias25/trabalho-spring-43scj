package br.com.autorizada.apispring.controllers;

import br.com.autorizada.apispring.entities.Autorizada;
import br.com.autorizada.apispring.repositories.AutorizadaRepository;
import br.com.autorizada.apispring.services.ConsultarCartaoCadastado;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/autorizada")
public class AtorizadaController {
    @Autowired
    private AutorizadaRepository autorizadaRepository;

    public void consultarCartaoCadastado(Autorizada autorizada) throws IOException, InterruptedException, UnirestException {
        ConsultarCartaoCadastado consultarCartaoCadastado = new ConsultarCartaoCadastado();
        consultarCartaoCadastado.consultaCartao(autorizada);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Autorizada cadastrar(@RequestBody Autorizada autorizada) throws IOException, InterruptedException, UnirestException {
        this.consultarCartaoCadastado(autorizada);
        return autorizadaRepository.save(autorizada);
    }

    @GetMapping
    public List<Autorizada> listar() {
        return autorizadaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Autorizada buscarPorId(@PathVariable Long id) {
        var cartaoOptional = autorizadaRepository.findById(id);
        if (cartaoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return cartaoOptional.get();
    }
}