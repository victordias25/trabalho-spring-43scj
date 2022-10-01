package br.com.autorizada.apispring.repositories;

import br.com.autorizada.apispring.entities.Autorizada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorizadaRepository extends JpaRepository<Autorizada, Long> {
}