package br.com.autorizada.apispring.services;

import br.com.autorizada.apispring.entities.Autorizada;
import br.com.autorizada.apispring.repositories.AutorizadaRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarCartaoCadastado {
    @Autowired
    private AutorizadaRepository autorizadaRepository;
    public void consultaCartao(Autorizada autorizada) throws UnirestException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8090/alunos" + "/find?cartaoCredito=" + autorizada.getCartaoCredito()))
                .header("Content-Type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.ofString("  {\n        \"cartaoCredito\": \"cartaoCredito\",\n}"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.body().toString().equals("null")) {
            autorizada.setStatusTransacao("Recusado. Cartão de Crédito incorreto");
        } else {
            autorizada.setStatusTransacao("Autorizado");
            EnvioTransacaoAPIExterna envioAPIExterna = new EnvioTransacaoAPIExterna();
            envioAPIExterna.envioApi(autorizada);
        }
    }
}
