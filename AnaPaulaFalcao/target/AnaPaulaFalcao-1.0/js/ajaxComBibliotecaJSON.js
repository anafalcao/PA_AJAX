function getDadosMedidores(){
    var url = "controller?" +
         
              "medidor=" + document.getElementById("medidor").value +
              "&periodo=" + document.getElementById("periodo").value +
              "&graficotabela=" + document.getElementById("graficotabela").value +
              "&data=" + document.getElementById("data").value;
        
    var ajaxRequest = new XMLHttpRequest();
    ajaxRequest.open("GET", url);
    ajaxRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

    ajaxRequest.onreadystatechange = function() {
        
        if(ajaxRequest.readyState===4 && ajaxRequest.status===200){
            console.log("response: ", ajaxRequest.responseText);
            var parsedJson = JSON.parse(ajaxRequest.responseText);
            if (parsedJson.data) {
                var tBody = document.getElementById("content");
                
                while (tBody.firstChild) {
                    tBody.removeChild(tBody.firstChild);
                }
                
                parsedJson.data.map((row) => {
                    console.log("row", row);
                    var newRow = document.createElement("tr");
                    var tdDataHora = document.createElement("td");
                    tdDataHora.appendChild(document.createTextNode(row.datahora));
                    newRow.appendChild(tdDataHora);
                    
                    var tdUmidade = document.createElement("td");
                    tdUmidade.appendChild(document.createTextNode(Number.parseFloat(row.umidade).toFixed(2)));
                    newRow.appendChild(tdUmidade);
                    
                    var tdTemperatura = document.createElement("td");
                    tdTemperatura.appendChild(document.createTextNode(Number.parseFloat(row.temperatura).toFixed(2)));
                    newRow.appendChild(tdTemperatura);
                    
                    tBody.appendChild(newRow);
                });
            }
//            var respostaJSON = eval("(" + ajaxRequest.responseText + ")");
//            document.getElementById("medidor").value = respostaJSON.medidor;
//            document.getElementById("periodo").value = respostaJSON.medidor.periodo;
//            document.getElementById("data").value = respostaJSON.medidor.data;
            //document.getElementById("COMPLEMENTOS").value = respostaJSON.endereco.complementos;

            //for(i=0;i<respostaJSON.telefones.length;i++){
               // if(respostaJSON.telefones[i]!==undefined){
                    //inserirLinhaNaTabela(respostaJSON,i);
                //}
            //}
        }
    };
    ajaxRequest.send(null);
}

function inserirLinhaNaTabela(resp,i){
    var telefone = document.getElementById("TELEFONES");
    var tr = document.createElement("tr");
    var td = document.createElement("td");
    var texto = document.createTextNode(resp.telefones[i]);

    var linha = telefone.appendChild(tr);
    var coluna = linha.appendChild(td);
    coluna.appendChild(texto);
}
            
            



