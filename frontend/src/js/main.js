"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
//Função para pegar os cards
function getCartas(deck_id, qtd) {
    return __awaiter(this, void 0, void 0, function* () {
        const response = yield fetch(`http://localhost:8080/deck/cartas/${deck_id}/${qtd}`);
        const data = yield response.json();
        constroi(data.cards);
    });
}
//Função que cria o deck de cards
function getDeck() {
    return __awaiter(this, void 0, void 0, function* () {
        const response = yield fetch("http://localhost:8080/deck");
        const data = yield response.json();
        getCartas(data.deck_id, 20);
    });
}
getDeck();
//Função para construir os cards no html e mostrar o vencedor
function constroi(cartas) {
    const texto = document.createElement("p");
    texto.textContent = "Jogador 1";
    const main = document.querySelector("main");
    let cont = 0;
    let maiorSoma = 0;
    let jogadorVencedor = '';
    //Criando as div
    for (let i = 0; i < 4; i++) {
        let soma = 0;
        const divCartas = document.createElement("div");
        divCartas.setAttribute('class', "divCard");
        const cardItem = document.createElement("div");
        cardItem.setAttribute('class', 'cards');
        divCartas.appendChild(texto);
        divCartas.appendChild(cardItem);
        //Inserindo as cartas nas divs e somando os valores das cartas
        for (let j = 0; j < 5; j++) {
            console.log(cartas[cont].value);
            switch (cartas[cont].value) {
                case "ACE":
                    soma += 1;
                    break;
                case "JACK":
                    soma += 11;
                    break;
                case "KING":
                    soma += 13;
                    break;
                case "QUEEN":
                    soma += 12;
                    break;
                default:
                    soma += Number(cartas[cont].value);
            }
            const img = document.createElement("img");
            img.src = cartas[cont].image;
            cardItem.appendChild(img);
            cont++;
        }
        if (soma > maiorSoma) {
            maiorSoma = soma;
            jogadorVencedor = `jogador ${i + 1}`;
        }
        main === null || main === void 0 ? void 0 : main.appendChild(divCartas);
    }
    const divJogadorVencedor = document.querySelector('.jogadorVencedor');
    const span = document.createElement("span");
    span.textContent = `O jogador vencedor é o ${jogadorVencedor}`;
    setTimeout(() => {
        divJogadorVencedor === null || divJogadorVencedor === void 0 ? void 0 : divJogadorVencedor.appendChild(span);
    }, 1500);
}
