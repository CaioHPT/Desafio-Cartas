interface Cartas {
    code: number;
    image: string;
    images: {
        png: string,
        svg: string
    }
    suit: string
    value: string
}

//Função para pegar os cards
async function getCartas(deck_id: string, qtd: number) {
    const response = await fetch(`http://localhost:8080/deck/cartas/${deck_id}/${qtd}`)
    const data = await response.json()

    constroi(data.cards)
}

//Função que cria o deck de cards
async function getDeck() {
    const response = await fetch("http://localhost:8080/deck")
    const data = await response.json()

    getCartas(data.deck_id, 20)
}

getDeck()

//Função para construir os cards no html e mostrar o vencedor
function constroi(cartas: Cartas[]) {
    const texto = document.createElement("p")
    texto.textContent = "Jogador 1"

    const main = document.querySelector("main")

    let cont: number = 0
    let maiorSoma: number = 0
    let jogadorVencedor: string = ''
    let empate: boolean = false

    //Criando as div
    for (let i = 0; i < 4; i++) {
        let soma: number = 0
        const divCartas = document.createElement("div")

        divCartas.setAttribute('class', "divCard")

        const cardItem = document.createElement("div")

        cardItem.setAttribute('class', 'cards')
        divCartas.appendChild(texto)
        divCartas.appendChild(cardItem)

        //Inserindo as cartas nas divs e somando os valores das cartas
        for (let j = 0; j < 5; j++) {
            console.log(cartas[cont].value)
            switch (cartas[cont].value) {
                case "ACE":
                    soma += 1
                    break
                case "JACK":
                    soma += 11
                    break
                case "KING":
                    soma += 13
                    break
                case "QUEEN":
                    soma += 12
                    break
                default:
                    soma += Number(cartas[cont].value)
            }
            const img = document.createElement("img")
            img.src = cartas[cont].image
            cardItem.appendChild(img)
            cont++
        }

        if (soma > maiorSoma) {
            maiorSoma = soma
            jogadorVencedor = `jogador ${i + 1}`
            empate = false
        } else if (soma === maiorSoma) {
            empate = true
        }

        main?.appendChild(divCartas)
    }

    
    if (empate) {
        const divResultado = document.querySelector('.resultado')
        const span = document.createElement("span")

        span.textContent = `EMPATE`

        setTimeout(() => {
            divResultado?.appendChild(span)
            for(let i = 0; i < 4; i++){
                const span = document.createElement("span")
                span.style.display = "block"
                span.textContent = `Jogador ${i + 1}`
                divResultado?.appendChild(span)
            }
        }, 1500)
    } else {
        const divResultado = document.querySelector('.resultado')
        const span = document.createElement("span")

        span.textContent = `O jogador vencedor é o ${jogadorVencedor}`

        setTimeout(() => {
            divResultado?.appendChild(span)
        }, 1500)
    }
}

