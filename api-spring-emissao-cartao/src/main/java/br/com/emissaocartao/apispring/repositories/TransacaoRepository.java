package br.com.emissaocartao.apispring.repositories;

import br.com.emissaocartao.apispring.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByCartaoCredito(String CartaoCredito);
}