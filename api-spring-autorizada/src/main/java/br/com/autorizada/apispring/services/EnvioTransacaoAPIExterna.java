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

public class EnvioTransacaoAPIExterna {
    @Autowired
    private AutorizadaRepository autorizadaRepository;

    public void envioApi(Autorizada autorizada) throws UnirestException, IOException, InterruptedException {
        String cartaoCredito = String.valueOf(autorizada.getCartaoCredito());
        String valorTotal = String.valueOf(autorizada.getValorTotal());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8090/transacao"))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString("  {\n        " +
                        "\"cartaoCredito\": \""+cartaoCredito+"\",\n        " +
                        "\"valorTotal\": \""+valorTotal+"\",\n        " +
                        "\"statusTransacao\": \"Autorizado\"\n    }"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
