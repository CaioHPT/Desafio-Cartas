package br.com.caio.klabDesafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caio.klabDesafio.service.CartasService;

@RestController
@RequestMapping("/deck")
@CrossOrigin(origins = "*")
public class CartasController {
	
	@Autowired
	private CartasService cartasService;
	
	@GetMapping
	public Object getDeck(){
		try {
			return cartasService.getDecks();
		}catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	@GetMapping("/cartas/{deck_id}/{qtd}")
	public Object getCartas(@PathVariable Integer qtd, @PathVariable String deck_id) {
		try {
			return cartasService.getCartas(qtd, deck_id);
		}catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
}
