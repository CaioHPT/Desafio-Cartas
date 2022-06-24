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
function getCards(deck_id, qtd) {
    return __awaiter(this, void 0, void 0, function* () {
        const response = yield fetch(`http://localhost:8080/deck/cartas/${deck_id}/${qtd}`);
        const data = yield response.json();
        constroi(data.cards);
    });
}
function getDeck() {
    return __awaiter(this, void 0, void 0, function* () {
        const response = yield fetch("http://localhost:8080/deck");
        const data = yield response.json();
        getCards(data.deck_id, 20);
    });
}
getDeck();
function constroi(cards) {
    const texto = document.createElement("p");
    texto.textContent = "Jogador 1";
    const main = document.querySelector("main");
    let cont = 0;
    let maiorSoma = 0;
    let jogadorVencedor = '';
    for (let i = 0; i < 4; i++) {
        let soma = 0;
        const divCards = document.createElement("div");
        divCards.setAttribute('class', "divCard");
        const cardItem = document.createElement("div");
        cardItem.setAttribute('class', 'cards');
        divCards.appendChild(texto);
        divCards.appendChild(cardItem);
        for (let j = 0; j < 5; j++) {
            console.log(cards[cont].value);
            switch (cards[cont].value) {
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
                    soma += Number(cards[cont].value);
            }
            const img = document.createElement("img");
            img.src = cards[cont].image;
            cardItem.appendChild(img);
            cont++;
        }
        if (soma > maiorSoma) {
            maiorSoma = soma;
            jogadorVencedor = `jogador ${i + 1}`;
        }
        main === null || main === void 0 ? void 0 : main.appendChild(divCards);
    }
    const divJogadorVencedor = document.querySelector('.jogadorVencedor');
    const span = document.createElement("span");
    span.textContent = `O jogador vencedor é o ${jogadorVencedor}`;
    setTimeout(() => {
        divJogadorVencedor === null || divJogadorVencedor === void 0 ? void 0 : divJogadorVencedor.appendChild(span);
    }, 1500);
}
