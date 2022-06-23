package br.com.caio.klabDesafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.caio.klabDesafio.model.Partida;
import br.com.caio.klabDesafio.repository.PartidaRepository;

@Service
public class PartidaService {

	@Autowired
	private PartidaRepository partidaRepository;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void save(Partida partida) {
		try {
			partidaRepository.save(partida);
		}catch(Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Partida delete(Long id) {
		try {
			Optional<Partida> partida = partidaRepository.findById(id);
			if(partida.isPresent()) {
				partidaRepository.delete(partida.get());
				return partida.get();
			}else {
				return null;
			}
		}catch(Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	private Partida update(Partida partida) {
		try {
			Optional<Partida> optionalPartida = partidaRepository.findById(partida.getId());
			if(optionalPartida.isPresent()) {
				partidaRepository.save(partida);
				return partida;
			}else {
				return null;
			}
		}catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	 @Transactional(readOnly = true)
	 public Partida getById(Long id) {
		 try {
			Optional<Partida> partida = partidaRepository.findById(id);
			if(partida.isPresent()) {
				return partida.get();
			}
			else {
				return null;
			}
		}catch(Exception exception) {
			throw new RuntimeException(exception);
		}
	 }
	 
	 @Transactional(readOnly = true)
	 public List<Partida> getAll(){
		try {
			return partidaRepository.findAll();	
		}catch(Exception exception) {
			throw new RuntimeException(exception);
		}
	 }
}
