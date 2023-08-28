let pregunta =0;
let seleccion =0;
let escala =0;

// P    S     E

function crearPregunta(){
    const inputContainer = document.getElementById("inputContainer");
    const newElement = document.createElement("div");
    const newInput = document.createElement("input");
    const newlabel = document.createElement("label");
    newElement.id = `P_${pregunta}`
    newlabel.textContent = "Pregunta:";
    newlabel.setAttribute("for", `P${pregunta}`);
    newInput.placeholder = "Enter something";
    newInput.name = `P${pregunta}`;
    newElement.append(newlabel, newInput)
    inputContainer.appendChild(newElement);
    pregunta++;
}

function crearSeleccion(){
    const inputContainer = document.getElementById("inputContainer");
    const newElement = document.createElement("div");
    const newInput = document.createElement("input");
    const newlabel = document.createElement("label");
    const oculto = document.createElement("input");
    newElement.id = `S_${seleccion}`
    newlabel.textContent = "Pregunta:";
    newlabel.setAttribute("for", `S${seleccion}`);
    newInput.placeholder = "Enter something";
    newInput.name = `S${seleccion}`;
    newElement.append(newlabel, newInput)
    for (let index = 0; index < document.getElementById("cantopt").value; index++) {
        const newInput2 = document.createElement("input");
        newInput2.placeholder = "Enter something";
        newInput2.name = `${seleccion}S${index}`;
        newElement.append(newInput2);
    }
    oculto.name = `S${seleccion}C`;
    oculto.type = "hidden";
    oculto.value = document.getElementById("cantopt").value;
    newElement.append(oculto);

    inputContainer.appendChild(newElement);
    seleccion++;
}

function crearEscala() {
    const inputContainer = document.getElementById("inputContainer");
    const newElement = document.createElement("div");
    const newInput = document.createElement("input");
    const newLabel = document.createElement("label");

    newElement.id = `E_${escala}`;
    newLabel.textContent = "Pregunta para escala:";
    newLabel.setAttribute("for", `E${escala}`);

    newInput.placeholder = "Enter something";
    newInput.name = `E${escala}`;

    const minimoLabel = document.createElement("label");
    minimoLabel.textContent = "Minimo :";
    minimoLabel.setAttribute("for", `minimo_${escala}`);
    const minimoInput = document.createElement("input");
    minimoInput.type = "number";
    minimoInput.name = `minimo_${escala}`;

    const maximoLabel = document.createElement("label");
    maximoLabel.textContent = "Maximo :";
    maximoLabel.setAttribute("for", `maximo_${escala}`);
    const maximoInput = document.createElement("input");
    maximoInput.type = "number";
    maximoInput.name = `maximo_${escala}`;

    const pasosLabel = document.createElement("label");
    pasosLabel.textContent = "Aumento :";
    pasosLabel.setAttribute("for", `pasos_${escala}`);
    const pasosInput = document.createElement("input");
    pasosInput.type = "number";
    pasosInput.name = `pasos_${escala}`;

    newElement.append(newLabel, newInput, minimoLabel, minimoInput, maximoLabel, maximoInput, pasosLabel, pasosInput);
    inputContainer.appendChild(newElement);

    escala++;
}

function removeLastInput() {
    const inputContainer = document.getElementById("inputContainer");
    const lastInput = inputContainer.lastElementChild;

    if (lastInput) {
        if(lastInput.id[0] === 'P'){
            pregunta--;
        }else if(lastInput.id[0] === 'S'){
            seleccion--;
        }else if(lastInput.id[0] === 'E'){
            escala--;
        }
        inputContainer.removeChild(lastInput);
    }
}


function cambiarValor() {
    document.getElementById("campoOculto1").value = pregunta;
    document.getElementById("campoOculto2").value = seleccion;
    document.getElementById("campoOculto3").value = escala;
}

function check(){
    if(pregunta === 0 && seleccion ===0 & escala ===0){
        event.preventDefault();
        alert("No se puede enviar el formulario");
    }
}
/*let cantCampos =0;
function createInputField() {
    const inputContainer = document.getElementById("inputContainer");

    const newInput = document.createElement("input");
    const newInput2 = document.createElement("input");
    const newLink = document.createElement("a");
    const newLink2 = document.createElement("a");
    newLink.textContent = "Pregunta:";
    inputContainer.appendChild(newLink);
    newInput.type = "text";
    newInput.placeholder = "Enter something";
    newInput.id = `Text${cantCampos}Q`;
    inputContainer.appendChild(newInput);
    newLink.textContent = "Respuesta:";
    inputContainer.appendChild(newLink);
    newInput2.type = "text";
    newInput2.placeholder = "Enter something";
    newInput2.id = `Text${cantCampos}R`;
    inputContainer.appendChild(newInput2);
    cantCampos++;
}
function removeLastInput() {
    const inputContainer = document.getElementById("inputContainer");
    inputContainer.innerHTML = "";
    cantCampos = 0;
}


function mostrarValor() {
    const slider = document.getElementById('miSlider');
    const valorActualSpan = document.getElementById('valorActual');
    valorActualSpan.textContent = slider.value;
}*/