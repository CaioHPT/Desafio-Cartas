package br.com.caio.klabDesafio.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartasService {
		
	public Object getDecks(){
		String url = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";
		RestTemplate restTemplate = new RestTemplate();
		Object res = restTemplate.getForObject(url, Object.class);
		return res;
	}

	public Object getCartas(Integer qtd, String deck_id) {
		String url = "https://deckofcardsapi.com/api/deck/" + deck_id + "/draw/?count=" + qtd;
		RestTemplate restTemplate = new RestTemplate();
		Object res = restTemplate.getForObject(url, Object.class);
		return res;
	}
	
}
