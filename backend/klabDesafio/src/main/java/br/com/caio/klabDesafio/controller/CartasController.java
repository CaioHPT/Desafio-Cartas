package br.com.caio.klabDesafio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/deck")
public class CartasController {
	
	@GetMapping
	public Object getDeck(){
		String url = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";
		RestTemplate restTemplate = new RestTemplate();
		Object res = restTemplate.getForObject(url, Object.class);
		return res;
	}
	
	@GetMapping("/cartas/{deck_id}/{qtd}")
	public Object getCartas(@PathVariable Integer qtd, @PathVariable String deck_id) {
		String url = "https://deckofcardsapi.com/api/deck/" + deck_id + "/draw/?count=" + qtd;
		RestTemplate restTemplate = new RestTemplate();
		Object res = restTemplate.getForObject(url, Object.class);
		return res;
	}
}
