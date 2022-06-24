"use strict";
async function getCards(deck_id, qtd) {
    const response = await fetch(`http://localhost:8080/deck/cartas/${deck_id}/${qtd}`);
    const data = await response.json();
    constroi(data.cards);
}
async function getDeck() {
    const response = await fetch("http://localhost:8080/deck");
    const data = await response.json();
    getCards(data.deck_id, 20);
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
        main?.appendChild(divCards);
    }
}
