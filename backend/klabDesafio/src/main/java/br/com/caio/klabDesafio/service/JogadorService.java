package br.com.caio.klabDesafio.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.caio.klabDesafio.model.Jogador;
import br.com.caio.klabDesafio.repository.JogadorRepository;

@Service
public class JogadorService {

	@Autowired
	private JogadorRepository jogadorRepository;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void save(Jogador jogador) {
		try {
			jogadorRepository.save(jogador);
		}catch(Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Jogador delete(Long id) {
		try {
			Optional<Jogador> jogador = jogadorRepository.findById(id);
			if(jogador.isPresent()) {
				jogadorRepository.delete(jogador.get());
				return jogador.get();
			}else {
				return null;
			}
		}catch(Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	private Jogador update(Jogador jogador) {
		try {
			Optional<Jogador> optionalJogador = jogadorRepository.findById(jogador.getId());
			if(optionalJogador.isPresent()) {
				jogadorRepository.save(jogador);
				return jogador;
			}else {
				return null;
			}
		}catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	 @Transactional(readOnly = true)
	 public Jogador getById(Long id) {
		 try {
			Optional<Jogador> jogador = jogadorRepository.findById(id);
			if(jogador.isPresent()) {
				return jogador.get();
			}
			else {
				return null;
			}
		}catch(Exception exception) {
			throw new RuntimeException(exception);
		}
	 }
	 
	 @Transactional(readOnly = true)
	 public List<Jogador> getAll(){
		try {
			return jogadorRepository.findAll();	
		}catch(Exception exception) {
			throw new RuntimeException(exception);
		}
	 }
}
