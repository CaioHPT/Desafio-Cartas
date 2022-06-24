"use strict";
async function getCards(deck_id, qtd) {
    const response = await fetch(`http://localhost:8080/deck/cartas/${deck_id}/${qtd}`);
    const data = await response.json();
    constroi(data.cards);
}
async function getDeck() {
    const response = await fetch("http://localhost:8080/deck");
    const data = await response.json();
    getCards(data.deck_id, 2);
}
getDeck();
function constroi(cards) {
    console.log(cards);
    const main = document.querySelector("main");
    cards.forEach(card => {
        const div = document.createElement("div");
        const img = document.createElement("img");
        img.src = card.image;
        div.appendChild(img);
        main?.appendChild(div);
    });
}
