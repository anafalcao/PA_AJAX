<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Automation</title>
        <link rel="stylesheet" type ="text/css" media="all" href="CSS/newcss.css">
        <script src="js/editarMedidores.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Allerta+Stencil&display=swap" rel="stylesheet">
    </head>
    <body onload="getMedidores()">
        <div class="header">
            <h1 style = "font-family: 'Allerta Stencil', Verdana; color: whitesmoke; text-shadow: 4px 4px 4px #000 ">Todos os medidores</h1>

        </div>
        
        <div class="container">
            <h2>Clique nos nomes para editá-los: </h2>  
  <table id="myTable">
    <thead>
      <tr>
        <th>NOME</th>
        <th>MEDIDOR</th>
      </tr>
    </thead>
    <tbody id="content">
    </tbody>
  </table>
  
            <input type="button" class="button" value="Voltar!" onclick="history.back()">
            <input type="button" class= "button" value="Salvar Edições" onclick="saveEdits()"/> 
            <button onclick="addRow()" class="button">Adicionar novo medidor</button>
</div>

                        
    </body>
</html>
