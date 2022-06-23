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

import br.com.caio.klabDesafio.model.Partida;
import br.com.caio.klabDesafio.service.PartidaService;

@RestController
@RequestMapping("/partida")
public class PartidaController {
	
	@Autowired
	private PartidaService partidaService;
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Partida>> getAll(){
		try {
			return ResponseEntity.ok().body(partidaService.getAll());
		}catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Partida> getById(@PathVariable Long id){
		try {
			Partida partida = partidaService.getById(id);
			if(!partida.equals(null)) {
				return ResponseEntity.ok().body(partida);
			}else {
				return ResponseEntity.noContent().build();
			}
		}catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<Partida> save(@RequestBody Partida partida){
		try {
			partidaService.save(partida);
			return ResponseEntity.ok().build();
		}catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Partida> delete(@PathVariable Long id){
		try {
			Partida partida = partidaService.delete(id);
			if(!partida.equals(null)) {
				return ResponseEntity.ok().body(partida);
			}else {
				return ResponseEntity.noContent().build();
			}
		}catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<Partida> update(@RequestBody Partida partida){
		try {
			Partida returnPartida = partidaService.update(partida);
			if(!returnPartida.equals(null)) {
				return ResponseEntity.ok().build();
			}else {
				return ResponseEntity.noContent().build();
			}
		}catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
}
