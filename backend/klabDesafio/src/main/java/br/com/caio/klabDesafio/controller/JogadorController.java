package br.com.caio.klabDesafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caio.klabDesafio.model.Jogador;
import br.com.caio.klabDesafio.service.JogadorService;

@RestController
@RequestMapping("/jogador")
public class JogadorController {
	
	@Autowired
	private JogadorService jogadorService;
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Jogador>> getAll(){
		try {
			return ResponseEntity.ok().body(jogadorService.getAll());
		}catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Jogador> getById(@PathVariable Long id){
		try {
			Jogador jogador = jogadorService.getById(id);
			if(!jogador.equals(null)) {
				return ResponseEntity.ok().body(jogador);
			}else {
				return ResponseEntity.noContent().build();
			}
		}catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<Jogador> save(@RequestBody Jogador jogador){
		try {
			jogadorService.save(jogador);
			return ResponseEntity.ok().body(jogador);
		}catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Jogador> delete(@PathVariable Long id){
		try {
			Jogador jogador = jogadorService.delete(id);
			if(!jogador.equals(null)) {
				return ResponseEntity.ok().body(jogador);
			}else {
				return ResponseEntity.noContent().build();
			}
		}catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<Jogador> update(@RequestBody Jogador jogador){
		try {
			Jogador returnJogador = jogadorService.update(jogador);
			if(!returnJogador.equals(null)) {
				return ResponseEntity.ok().body(returnJogador);
			}else {
				return ResponseEntity.noContent().build();
			}
		}catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
}
