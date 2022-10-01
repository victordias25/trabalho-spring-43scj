package br.com.emissaocartao.apispring.repositories;

import br.com.emissaocartao.apispring.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByCartaoCredito(String CartaoCredito);
}