package eel418.ajaxcombibliotecajson;

import dto.Parametros;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.*;
import mvc.modelo.AcessoBD;
import org.json.JSONArray;

public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String medidor = request.getParameter("medidor");
        String periodo = request.getParameter("periodo");
        String data = request.getParameter("data");
        String graficotabela = request.getParameter("graficotabela");
        
        try {
   
            JsonArrayBuilder resultado = new AcessoBD().getParametros(medidor, periodo, data);
            
             JsonObject resposta = Json.createObjectBuilder()
                     .add("data", resultado)
                     .build();
            
             out.print(resposta.toString());
             
        } catch (SQLException e) {
            out.print(e.getMessage());
        } finally {
            out.flush();
        };

//        JsonObject objetoJSON = Json.createObjectBuilder()
//                .add("nome", nomeFantasia)
//                .add("endereco", Json.createObjectBuilder()
//                        .add("logradouro", "Avenida Atlântica")
//                        .add("numero", "13")
//                        .add("complementos", "bloco C apto 601 fundos")
//                )
//                .add("telefones", Json.createArrayBuilder()
//                        .add("1-1234")
//                        .add("2-1234")
//                        .add("3-1234")
//                        .add("4-1234")
//                        .add("5-1234")
//                        .add("6-1234")
//                        .add("7-1234")
//                        .add("8-1234")
//                        .add("9-1234")
//                )
//                .build();

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
