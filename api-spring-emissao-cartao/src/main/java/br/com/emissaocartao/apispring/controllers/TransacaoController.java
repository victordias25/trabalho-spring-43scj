package br.com.emissaocartao.apispring.controllers;

import br.com.emissaocartao.apispring.entities.Transacao;
import br.com.emissaocartao.apispring.repositories.TransacaoRepository;
import br.com.emissaocartao.apispring.services.CartaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/transacao")

public class TransacaoController {
    @Autowired
    private TransacaoRepository transacaoRepository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Transacao cadastrar(@RequestBody Transacao transacao) throws CartaoException {
        return transacaoRepository.save(transacao);
    }

    @GetMapping
    public List<Transacao> listar() {
        return transacaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Transacao buscarPorId(@PathVariable Long id) {
        var transacaoOptional = transacaoRepository.findById(id);
        if (transacaoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return transacaoOptional.get();
    }

    @GetMapping(path = "/api/extrato")
    public List<Transacao> consultaExtrato(Transacao transacao) throws IOException {
        OutputStream os = new FileOutputStream("Extrato.txt");
        Writer wr = new OutputStreamWriter(os);
        BufferedWriter br = new BufferedWriter(wr);

        var transacaoOptional = transacaoRepository.findByCartaoCredito(transacao.getCartaoCredito());

        br.write("Extrato Cartão de Crédito nº " + transacao.getCartaoCredito());
        br.newLine();
        br.write("---------------------------------------------------");
        br.newLine();
        Double somaValorTotal = Double.valueOf(0);
        for (int i = 0; i < listar().size(); i++) {
            br.write(("R$ " + transacaoOptional.get(i).getValorTotal() + "."));
            br.write(" Data: " + transacaoOptional.get(i).getData() + ".");
            br.write(" Hora: " + transacaoOptional.get(i).getHora() + ".");
            double valorTotal = transacaoOptional.get(i).getValorTotal().doubleValue();
            somaValorTotal = valorTotal + somaValorTotal;
            br.newLine();
        }

        br.newLine();
        br.write("Valor Total: R$ " + somaValorTotal);
        br.newLine();
        br.write("---------------------------------------------------");
        br.close();
        return transacaoRepository.findByCartaoCredito(transacao.getCartaoCredito());
    }
}