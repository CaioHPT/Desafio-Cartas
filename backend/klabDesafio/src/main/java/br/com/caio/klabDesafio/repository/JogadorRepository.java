package br.com.caio.klabDesafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.caio.klabDesafio.model.Jogador;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long>{

}
