
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CadastrarAlunos {
    public void cadastrarAlunos() throws IOException, UnirestException {
        List<String> alunos = new ArrayList<>();

        FileInputStream stream = new FileInputStream("C:\\Users\\Victor Dias\\Downloads\\api-spring-fiap\\inclusao-cartao\\src\\lista_alunos.txt");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        while (linha != null) {
            if (!linha.trim().equals("") & !linha.trim().contains("---")) {
                alunos.add(linha);
            }
            linha = br.readLine();
        }
        for (int i = 0; i <= alunos.size() - 1; i++) {
            String nomeAluno = String.valueOf(alunos.get(i).substring(0, alunos.get(i).length() - 14)).trim();
            String cartaoCredito = String.valueOf(alunos.get(i).substring(alunos.get(i).length() - 14)).trim().replaceAll("-", "").replace(" ", "");
            System.out.print("Nome: " + nomeAluno);
            System.out.println(" Cartão de Crédito: " + cartaoCredito);

            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post("http://localhost:8090/alunos")
                    .header("Content-Type", "application/json")
                    .body("{\"nomeAluno\":\"" + nomeAluno + "\",\"cartaoCredito\":\"" + cartaoCredito
                            + "\"}")
                    .asString();
        }
    }
}
