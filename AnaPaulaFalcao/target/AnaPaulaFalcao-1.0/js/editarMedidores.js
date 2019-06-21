var medidoresData = [];
var medidoresToSave = [];

function getMedidores() {
    var url = "medidorcontroller"

    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open("GET", url);

    ajaxRequest.onreadystatechange = function () {

        if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200) {
            console.log("response: ", ajaxRequest.responseText);
            var parsedJson = JSON.parse(ajaxRequest.responseText);
            if (parsedJson.data) {
                medidoresData = parsedJson.data;
                var tBody = document.getElementById("content");

                while (tBody.firstChild) {
                    tBody.removeChild(tBody.firstChild);
                }

                parsedJson.data.map((row) => {
                    console.log("row", row);
                    var newRow = document.createElement("tr");
                    var tdNome = document.createElement("td");
                    tdNome.appendChild(document.createTextNode(row.nome));
                    tdNome.setAttribute("id", row.serial);
                    tdNome.setAttribute("contenteditable", true);
                    tdNome.addEventListener("input", medidorChangeListener)
                    newRow.appendChild(tdNome);

                    var tdTabela = document.createElement("td");
                    tdTabela.appendChild(document.createTextNode(row.tabela));
                    newRow.appendChild(tdTabela);

                    tBody.appendChild(newRow);
                });
            }
        }
    };
    ajaxRequest.send(null);
}

function medidorChangeListener(event) {
    console.log("medidorChangeListener")
    var alreadySavedMedidorIndex = medidoresToSave.findIndex((medidor) => {
        return medidor.serial == event.target.id
    });
    if (alreadySavedMedidorIndex >= 0){
        medidoresToSave[alreadySavedMedidorIndex].nome = event.target.innerText;
        return;
    }
    var changedMedidor = medidoresData.find((medidor) => medidor.serial == event.target.id);
    if (changedMedidor) {
        changedMedidor.nome = event.target.innerText;
        medidoresToSave.push(changedMedidor);
    }
    console.log("medidoresToSave",medidoresToSave)
}

function saveEdits() {
    var url = "medidorcontroller"

    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open("POST", url);

    ajaxRequest.onreadystatechange = function () {
        if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200) {
            console.log("response: ", ajaxRequest.responseText);
        }
    };
       ajaxRequest.send(JSON.stringify(medidoresToSave));
}

function addRow() {

    var table = document.getElementById("myTable");

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var nomeCell = row.insertCell(0);
    var nomeInput = document.createElement("td");
    nomeInput.setAttribute("contenteditable", true);
    nomeInput.id = rowCount;
    nomeInput.addEventListener("input", medidorChangeListener);
    nomeCell.appendChild(nomeInput);

    var tabelaCell = row.insertCell(1);
    tabelaCell.name = "tabela";
    tabelaCell.innerHTML = "medidor00" + rowCount;
    
    medidoresData.push({ serial: rowCount, nome: "", tabela: "medidor00" + rowCount });
}


